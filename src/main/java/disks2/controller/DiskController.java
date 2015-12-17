package disks2.controller;

import disks2.domain.User;
import disks2.domain.UserRole;
import disks2.service.DiskService;

import javax.servlet.http.HttpServletRequest;

import disks2.service.UserRoleService;
import disks2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * Created by adminvl on 21.10.2015.
 */
@Controller
public class DiskController {
    @Autowired
    private DiskService diskService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    private User getCurrentUser() {
        User user = null;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            user = userService.getUserByName(userDetail.getUsername());
        }
            return user;
    }

    @RequestMapping(value = { "/"}, method = RequestMethod.GET)
    public ModelAndView defaultPage() {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Обменник dvd дисков");
        model.addObject("message", "Осуществите авторизацию");
        model.setViewName("hello");
        return model;

    }

    private String getErrorMessage(HttpServletRequest request, String key) {
        if (key.equals("null fild"))
        {
            return "Некоректные значения полей";
        }
        Exception exception = (Exception) request.getSession().getAttribute(key);
        String error = "";
        if (exception instanceof BadCredentialsException) {
            error = "Неверный логин или пароль!";
        } else if (exception instanceof LockedException) {
            error = exception.getMessage();
        } else {
            error = "Неверный логин или пароль!";
        }

        return error;
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public ModelAndView accesssDenied() {

        ModelAndView model = new ModelAndView();

        // check if user is login
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            System.out.println(userDetail);

            model.addObject("login", userDetail.getUsername());

        }

        model.setViewName("error");
        return model;

    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView adminPage() {
        ModelAndView model = new ModelAndView();
        model.addObject("listUser", userService.listUser());
        model.addObject("newUser", new User());
        model.setViewName("admin");

        return model;

    }

    @RequestMapping(value="/addUser", method = RequestMethod.POST)
    public ModelAndView addUser(@ModelAttribute("newUser") User newUser, BindingResult result,
                                @RequestParam("role") String role) {
        try {
            newUser.setEnabled(true);
            Set<UserRole> userRoles = new HashSet<>();
            UserRole userRole = new UserRole();
            userRole.setRole(role);
            userRole.setUser(newUser);
            userRoles.add(userRole);
            newUser.setRoles(userRoles);

            userService.addUser(newUser);
            userRoleService.addUserRole(userRole);
        }
        catch (RuntimeException e)
        {
            return new ModelAndView("redirect:/admin");
        }
        return new ModelAndView("redirect:/admin");
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
        }
        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }
        model.setViewName("login");

        return model;

    }

    @RequestMapping(value = "/listOwnDisks")
    public ModelAndView listOwnDisks() {
        Map<String, Object> model = new HashMap<String, Object>();
        try
        {
            model.put("listOwnDisks", diskService.listOwnDisks(getCurrentUser().getId()));
        }
        catch (NullPointerException e)
        {
            return new ModelAndView("error");
        }
        return new ModelAndView("listOwnDisks", model);
    }

    @RequestMapping(value = "/listFreeDisks")
    public ModelAndView listFreeDisks() {
        Map<String, Object> model = new HashMap<String, Object>();
        try
        {
            model.put("listFreeDisks", diskService.listFreeDisks());
            model.put("listOwnDisksFromAllUsers", diskService.listOwnDisksFromAllUsers(getCurrentUser().getId()));
        }
        catch (NullPointerException e)
        {
            return new ModelAndView("error");
        }
        return new ModelAndView("listFreeDisks", model);
    }
    @RequestMapping(value = "/listTakenDisksByUser")
    public ModelAndView listTakenDisksByUser() {
        Map<String, Object> model = new HashMap<String, Object>();
        try
        {
            model.put("listTakenDisksByUser", diskService.listTakenDisksByUser(getCurrentUser().getId()));
        }
        catch (NullPointerException e)
        {
            return new ModelAndView("error");
        }
        return new ModelAndView("listTakenDisksByUser", model);
    }
    @RequestMapping(value = "/listTakenDisksFromUser")
    public ModelAndView listTakenDisksFromUser() {
        Map<String, Object> model = new HashMap<String, Object>();
        try
        {
            model.put("listTakenDisksFromUser", diskService.listTakenDisksFromUser(getCurrentUser().getId()));
        }
        catch (NullPointerException e)
        {
            return new ModelAndView("error");
        }
        return new ModelAndView("listTakenDisksFromUser", model);
    }
    @RequestMapping(value="/{id}/takeFreeDisk")
    public ModelAndView takeFreeDisk(@PathVariable Integer id)
    {
        diskService.takeFreeDisk(getCurrentUser().getId(), id);
        return new ModelAndView("redirect:/listFreeDisks");
    }
    @RequestMapping(value="/{diskId}/{fromId}/takeFreeDiskFromUser")
    public ModelAndView takeFreeDiskFromUser(@PathVariable Integer diskId,@PathVariable Integer fromId)
    {
        diskService.takeFreeDiskFromUser(getCurrentUser().getId(),diskId,fromId);
        return new ModelAndView("redirect:/listFreeDisks");
    }
    @RequestMapping(value="/{diskId}/{fromId}/returnDiskToUser")
    public ModelAndView returnDiskToUser(@PathVariable Integer diskId,@PathVariable Integer fromId)
    {
        diskService.returnDiskToUser(getCurrentUser().getId(),diskId,fromId);
        return new ModelAndView("redirect:/listTakenDisksByUser");
    }
    @RequestMapping(value="/{id}/returnOwnDisk")
    public ModelAndView returnOwnDisk(@PathVariable Integer id)
    {
        diskService.returnOwnDisk(getCurrentUser().getId(), id);
        return new ModelAndView("redirect:/listOwnDisks");
    }
}
