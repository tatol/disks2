package disks2.service;

import disks2.domain.User;

/**
 * Created by adminvl on 21.10.2015.
 */
public interface UserService {
    User getUser(String login, String password);
}
