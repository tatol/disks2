package disks2.controller;

import disks2.domain.Disk;
import disks2.domain.User;
import disks2.domain.UserRole;
import disks2.service.UserRoleService;
import disks2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
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


    @RequestMapping(value = "/login")
    public ResponseEntity<User> getUser(@RequestParam(value = "login", required = false) String login, @RequestParam(value = "password", required = false) String password) {

        User user = userService.getUserByName(login);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
}
