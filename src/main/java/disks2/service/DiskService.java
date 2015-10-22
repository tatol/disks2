package disks2.service;

import disks2.domain.Disk;

import java.util.List;

/**
 * Created by adminvl on 21.10.2015.
 */
public interface DiskService {
    List<Disk> listOwnDisks(Integer userId);
    List<Disk> listFreeDisks();
}
