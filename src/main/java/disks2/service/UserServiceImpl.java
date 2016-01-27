package disks2.service;

import disks2.domain.User;
import disks2.domain.UserRole;
import disks2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by adminvl on 10.12.2015.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Resource
    @Autowired
    private UserRepository userRepository;


    @Override
    public User getUserByName(String userName) {
        return userRepository.getUserByName(userName);
    }


    @Override
    public void addUser(User user) {
        Set<UserRole> userRoles = user.getRoles();
        Iterator<UserRole> it = userRoles.iterator();
        UserRole role = it.next();
        if ((user.getPassword().equals(""))||(user.getLogin().equals(""))||(role.getRole().equals("")))
        {
            throw new NullPointerException();
        }
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = user.getPassword();
        String criptoPassword = encoder.encode(password);
        user.setPassword(criptoPassword);
        userRepository.saveAndFlush(user);
    }

    @Override
    public List<User> listUser() {
        return userRepository.findAll();
    }
}
