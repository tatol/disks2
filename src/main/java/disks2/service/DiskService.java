package disks2.service;

import disks2.domain.Disk;


import java.util.List;

/**
 * Created by adminvl on 21.10.2015.
 */
public interface DiskService {
    List<Disk> listOwnDisks(Integer userId);
    List<Disk> listFreeDisks();

    void takeFreeDisk(Integer userId, Integer diskId);
    void returnOwnDisk(Integer userId, Integer diskId);
    void takeFreeDiskFromUser(Integer userId, Integer diskId, Integer fromId);
    void returnDiskToUser(Integer userId, Integer diskId, Integer fromId);

    List<Disk> listOwnDisksFromAllUsers(Integer currentUserId);
    List<Disk> listTakenDisksByUser(Integer userId);
    List<Disk> listTakenDisksFromUser(Integer userId);
}
