package disks2.service;

import disks2.dao.UserDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * Created by adminvl on 21.10.2015.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Transactional
    public Integer getUserId(String login, String password) {
        return userDAO.getUserId(login,password);
    }

}
