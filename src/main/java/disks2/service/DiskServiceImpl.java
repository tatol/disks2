package disks2.service;

import disks2.dao.DiskDAO;
import disks2.domain.Disk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by adminvl on 21.10.2015.
 */
@Service
public class DiskServiceImpl implements DiskService {
    @Autowired
    private DiskDAO diskDAO;

    @Transactional
    public List<Disk> listOwnDisks(Integer userId) {
        return diskDAO.listOwnDisks(userId);
    }
    @Transactional
    public List<Disk> listFreeDisks()
    {
        return diskDAO.listFreeDisks();
    }


    @Transactional
    public void takeFreeDisk(Integer userId, Integer diskId) {
        diskDAO.takeFreeDisk(userId, diskId);
    }
    @Transactional
    public void returnOwnDisk(Integer userId, Integer diskId) {
        diskDAO.returnOwnDisk(userId,diskId);
    }
    @Transactional
    public void takeFreeDiskFromUser(Integer userId, Integer diskId, Integer fromId) {
        diskDAO.takeFreeDiskFromUser(userId,diskId,fromId);
    }
    @Transactional
    public void returnDiskToUser(Integer userId, Integer diskId, Integer fromId) {
        diskDAO.returnDiskToUser(userId,diskId,fromId);
    }

    @Transactional
    public List<Disk> listOwnDisksFromAllUsers(Integer currentUserId) {
        return diskDAO.listOwnDisksFromAllUsers(currentUserId);
    }
    @Transactional
    public List<Disk> listTakenDisksByUser(Integer userId) {
        return diskDAO.listTakenDisksByUser(userId);
    }
    @Transactional
    public List<Disk> listTakenDisksFromUser(Integer userId) {
        return diskDAO.listTakenDisksFromUser(userId);
    }
}
