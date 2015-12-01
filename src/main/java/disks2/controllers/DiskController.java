package disks2.controllers;

import disks2.domain.User;
import disks2.form.LoginForm;
import disks2.service.DiskService;
import disks2.service.UserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;
/**
 * Created by adminvl on 21.10.2015.
 */
@Controller
public class DiskController {
    @Autowired
    private UserService userService;
    @Autowired
    private DiskService diskService;

    private User currentUser;

    @RequestMapping(value = "/error")
    public String error() {
        return "/error";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginForm(Map model) {
        LoginForm loginForm = new LoginForm();
        model.put("loginForm", loginForm);
        return "login";
    }
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String processLoginForm(@Valid LoginForm loginForm) {
        String result;
        currentUser = userService.getUser(loginForm.getLogin(), loginForm.getPassword());
        if (currentUser==null)
        {
            result=error();
        }
        else result="redirect:/login/listOwnDisks";
        return result;
    }

    @RequestMapping(value = "/login/listOwnDisks")
    public ModelAndView listOwnDisks() {
        Map<String, Object> model = new HashMap<String, Object>();
        try {
            model = new HashMap<String, Object>();
            model.put("listOwnDisks", diskService.listOwnDisks(currentUser.getId()));
        }
        catch (NullPointerException e)
        {
            return new ModelAndView("error");
        }
        return new ModelAndView("listOwnDisks", model);
    }

    @RequestMapping(value = "/login/listFreeDisks")
    public ModelAndView listFreeDisks() {
        Map<String, Object> model = new HashMap<String, Object>();
        try {
            model.put("listFreeDisks", diskService.listFreeDisks());
            model.put("listOwnDisksFromAllUsers", diskService.listOwnDisksFromAllUsers(currentUser.getId()));
        }
        catch (NullPointerException e)
        {
            return new ModelAndView("error");
        }
        return new ModelAndView("listFreeDisks", model);
    }
    @RequestMapping(value = "/login/listTakenDisksByUser")
    public ModelAndView listTakenDisksByUser() {
        Map<String, Object> model = new HashMap<String, Object>();
        try
        {
            model.put("listTakenDisksByUser", diskService.listTakenDisksByUser(currentUser.getId()));
        }
        catch (NullPointerException e)
        {
            return new ModelAndView("error");
        }
        return new ModelAndView("listTakenDisksByUser", model);
    }
    @RequestMapping(value = "/login/listTakenDisksFromUser")
    public ModelAndView listTakenDisksFromUser() {
        Map<String, Object> model = new HashMap<String, Object>();
        try {
            model.put("listTakenDisksFromUser", diskService.listTakenDisksFromUser(currentUser.getId()));
        }
        catch (NullPointerException e)
        {
            return new ModelAndView("error");
        }
        return new ModelAndView("listTakenDisksFromUser", model);
    }
    @RequestMapping(value="*/{id}/takeFreeDisk")
    public ModelAndView takeFreeDisk(@PathVariable Integer id)
    {
        diskService.takeFreeDisk(currentUser.getId(), id);
        return new ModelAndView("redirect:/login/listFreeDisks");
    }
    @RequestMapping(value="*/{diskId}/{fromId}/takeFreeDiskFromUser")
    public ModelAndView takeFreeDiskFromUser(@PathVariable Integer diskId,@PathVariable Integer fromId)
    {
        diskService.takeFreeDiskFromUser(currentUser.getId(),diskId,fromId);
        return new ModelAndView("redirect:/login/listFreeDisks");
    }
    @RequestMapping(value="*/{diskId}/{fromId}/returnDiskToUser")
    public ModelAndView returnDiskToUser(@PathVariable Integer diskId,@PathVariable Integer fromId)
    {
        diskService.returnDiskToUser(currentUser.getId(),diskId,fromId);
        return new ModelAndView("redirect:/login/listTakenDisksByUser");
    }
    @RequestMapping(value="*/{id}/returnOwnDisk")
    public ModelAndView returnOwnDisk(@PathVariable Integer id)
    {
        diskService.returnOwnDisk(currentUser.getId(), id);
        return new ModelAndView("redirect:/login/listOwnDisks");
    }
}
