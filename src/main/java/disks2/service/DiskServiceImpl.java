package disks2.service;

import disks2.domain.Disk;
import disks2.domain.User;
import disks2.repository.DiskRepository;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by adminvl on 21.10.2015.
 */
@Service
@Transactional
public class DiskServiceImpl implements DiskService {
    @Resource
    @Autowired
    private DiskRepository diskRepository;

    @Override
    public List<Disk> listOwnDisks(Integer userId) {
        return diskRepository.listOwnDisks(userId);
    }

    @Override
    public List<Disk> listFreeDisks()
    {
        return diskRepository.listFreeDisks();
    }

    @Override
    public void takeFreeDisk(Integer userId, Integer diskId) {
        User user = diskRepository.getUserById(userId);
        Disk disk = diskRepository.getDiskById(diskId);
        disk.setUser(user);
        diskRepository.saveAndFlush(disk);
    }

    @Override
    public void returnOwnDisk(Integer userId, Integer diskId) {
        User user = diskRepository.getUserById(userId);
        Disk disk = diskRepository.getDiskByIdAndUser(user,diskId);
        disk.setUser(null);
        diskRepository.saveAndFlush(disk);
    }

    @Override
    public void takeFreeDiskFromUser(Integer userId, Integer diskId, Integer fromId) {
        User user = diskRepository.getUserById(userId);
        User fromUser = diskRepository.getUserById(fromId);
        Disk disk = diskRepository.getDiskByIdAndUser(fromUser,diskId);
        disk.setUser(user);
        disk.setFromUser(fromUser);
        diskRepository.saveAndFlush(disk);
    }

    @Override
    public void returnDiskToUser(Integer userId, Integer diskId, Integer fromId) {
        User user = diskRepository.getUserById(userId);
        User fromUser = diskRepository.getUserById(fromId);
        Disk disk = diskRepository.getDiskByIdAndUser(user,diskId);
        disk.setUser(fromUser);
        disk.setFromUser(null);
        diskRepository.saveAndFlush(disk);
    }


    @Override
    public List<Disk> listOwnDisksFromAllUsers(Integer currentUserId) {
        return diskRepository.listOwnDisksFromAllUsers(currentUserId);
    }

    @Override
    public List<Disk> listTakenDisksByUser(Integer userId) {
        return diskRepository.listTakenDisksByUser(userId);
    }

    @Override
    public List<Disk> listTakenDisksFromUser(Integer userId) {
        return diskRepository.listTakenDisksFromUser(userId);
    }
}
