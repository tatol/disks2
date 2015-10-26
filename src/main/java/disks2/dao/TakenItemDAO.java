package disks2.dao;

import disks2.domain.Disk;
import disks2.domain.TakenItem;
import disks2.domain.User;

import java.util.List;

/**
 * Created by adminvl on 23.10.2015.
 */
public interface TakenItemDAO {
    Disk getDiskById(Integer diskId);
    User getUserById(Integer userId);
    void takeFreeDisk(Integer userId, Integer diskId);
    void takeFreeDiskFromUser(Integer userId, Integer diskId, Integer fromId);
    void returnDiskToUser(Integer userId, Integer diskId, Integer fromId);
    void returnOwnDisk(Integer userId, Integer diskId);
    List<TakenItem> listOwnDisksFromAllUsers(Integer currentUserId);
    List<TakenItem> listTakenDisksFromUser(Integer userId);
    List<TakenItem> listTakenDisksByUser(Integer userId);
}
