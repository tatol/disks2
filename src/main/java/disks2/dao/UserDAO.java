package disks2.dao;

import disks2.domain.User;

/**
 * Created by adminvl on 21.10.2015.
 */
public interface UserDAO {
    Integer getUserId(String userName, String userPassword);
}
