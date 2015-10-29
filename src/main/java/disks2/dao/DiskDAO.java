package disks2.dao;

import disks2.domain.Disk;

import java.util.List;

/**
 * Created by adminvl on 21.10.2015.
 */
public interface DiskDAO {
    List<Disk> listOwnDisks(Integer userId);//список собственных(взятых из хранилища) дисков пользователя
    List<Disk> listFreeDisks();//список дисков в общем хранилище
}
