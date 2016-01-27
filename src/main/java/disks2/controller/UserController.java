package disks2.controller;

import disks2.domain.User;
import disks2.domain.UserRole;
import disks2.service.UserRoleService;
import disks2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by adminvl on 22.01.2016.
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;


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

}
