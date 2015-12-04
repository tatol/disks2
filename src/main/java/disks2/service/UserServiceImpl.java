package disks2.service;

import disks2.domain.User;
import disks2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by adminvl on 21.10.2015.
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserRepository userRepository;

    @Transactional
    public User getUser(String login, String password) {
        return userRepository.getUser(login, password);
    }

}
