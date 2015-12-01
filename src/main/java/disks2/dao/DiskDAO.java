package disks2.dao;

import disks2.domain.Disk;
import disks2.domain.User;

import java.util.List;

/**
 * Created by adminvl on 21.10.2015.
 */
public interface DiskDAO {
    List<Disk> listOwnDisks(Integer userId);//список собственных(взятых из хранилища) дисков пользователя
    List<Disk> listFreeDisks();//список дисков в общем хранилище

    Disk getDiskById(Integer diskId);
    User getUserById(Integer userId);
    void takeFreeDisk(Integer userId, Integer diskId);//взять диск из общего хранилища
    void takeFreeDiskFromUser(Integer userId, Integer diskId, Integer fromId);//взять диск у пользователя
    void returnDiskToUser(Integer userId, Integer diskId, Integer fromId);//вернуть диск пользователю
    void returnOwnDisk(Integer userId, Integer diskId);//вернуть диск в общее хранилище
    //список свободных(взятых из общего хранилища) дисков всех пользователей, кроме текущего
    List<Disk> listOwnDisksFromAllUsers(Integer currentUserId);
    List<Disk> listTakenDisksFromUser(Integer userId);//список дисков взятых у пользователя другими пользователями
    List<Disk> listTakenDisksByUser(Integer userId);//список дисков взятых пользователем у других пользователей
}
