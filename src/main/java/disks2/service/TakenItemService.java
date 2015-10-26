package disks2.service;

import disks2.domain.TakenItem;

import java.util.List;

/**
 * Created by adminvl on 23.10.2015.
 */
public interface TakenItemService {
    void takeFreeDisk(Integer userId, Integer diskId);
    void returnOwnDisk(Integer userId, Integer diskId);
    List<TakenItem> listOwnDisksFromAllUsers(Integer currentUserId);
    List<TakenItem> listTakenDisksByUser(Integer userId);
    List<TakenItem> listTakenDisksFromUser(Integer userId);
}
