package disks2.controller;

import disks2.domain.Credentials;
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


    @RequestMapping(value = "/admin")
    public ResponseEntity<List<User>> listUser() {
        List<User> userList = userService.listUser();
        return new ResponseEntity<List<User>>(userList,HttpStatus.OK);
    }
    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public void createUser(@RequestBody Credentials credentials) {
        User newUser = new User();
        newUser.setEnabled(true);
        newUser.setLogin(credentials.getLogin());
        newUser.setPassword(credentials.getPassword());
        Set<UserRole> userRoles = new HashSet<>();
        UserRole userRole = new UserRole();
        userRole.setRole(credentials.getRole());
        userRole.setUser(newUser);
        userRoles.add(userRole);
        newUser.setRoles(userRoles);

        userService.addUser(newUser);
        userRoleService.addUserRole(userRole);
    }
    @RequestMapping(value = "/user")
    public ResponseEntity<User> getUser(Principal principal) {
        User user = userService.getUserByName(principal.getName());
        return new ResponseEntity<User>(user,HttpStatus.OK);
    }
}
