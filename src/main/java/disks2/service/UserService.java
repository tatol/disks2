package disks2.service;

import disks2.domain.User;
import disks2.domain.UserRole;

import java.util.List;
import java.util.Set;

/**
 * Created by adminvl on 10.12.2015.
 */
public interface UserService {
    User getUserByName(String userName);
    void addUser(User user);
    List<User> listUser();
}
