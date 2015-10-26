package disks2.service;

import disks2.dao.DiskDAO;
import disks2.dao.TakenItemDAO;
import disks2.domain.TakenItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by adminvl on 23.10.2015.
 */
@Service
public class TakenItemServiceImpl implements TakenItemService {
    @Autowired
    private TakenItemDAO takenItemDAO;
    @Transactional
    public void takeFreeDisk(Integer userId, Integer diskId) {
        takenItemDAO.takeFreeDisk(userId, diskId);
    }
    @Transactional
    public void returnOwnDisk(Integer userId, Integer diskId) {
        takenItemDAO.returnOwnDisk(userId,diskId);
    }
    @Transactional
    public List<TakenItem> listOwnDisksFromAllUsers(Integer currentUserId) {
        return takenItemDAO.listOwnDisksFromAllUsers(currentUserId);
    }
    @Transactional
    public List<TakenItem> listTakenDisksByUser(Integer userId) {
        return takenItemDAO.listTakenDisksByUser(userId);
    }
    @Transactional
    public List<TakenItem> listTakenDisksFromUser(Integer userId) {
        return takenItemDAO.listTakenDisksFromUser(userId);
    }
}
