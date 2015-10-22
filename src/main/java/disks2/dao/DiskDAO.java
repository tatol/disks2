package disks2.dao;

import disks2.domain.Disk;

import java.util.List;

/**
 * Created by adminvl on 21.10.2015.
 */
public interface DiskDAO {
    List<Disk> listOwnDisks(Integer userId);
    List<Disk> listFreeDisks();
    List<Disk> listTakenDisksByUser(Integer userId);
}
