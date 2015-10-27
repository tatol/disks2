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
    void takeFreeDisk(Integer userId, Integer diskId);//взять диск из общего хранилища
    void takeFreeDiskFromUser(Integer userId, Integer diskId, Integer fromId);//взять диск у пользователя
    void returnDiskToUser(Integer userId, Integer diskId, Integer fromId);//вернуть диск пользователю
    void returnOwnDisk(Integer userId, Integer diskId);//вернуть диск в общее хранилище
    //список свободных(взятых из общего хранилища) дисков всех пользователей, кроме текущего
    List<TakenItem> listOwnDisksFromAllUsers(Integer currentUserId);
    List<TakenItem> listTakenDisksFromUser(Integer userId);//список дисков взятых у пользователя другими пользователями
    List<TakenItem> listTakenDisksByUser(Integer userId);//список дисков взятых пользователем у других пользователей
}
