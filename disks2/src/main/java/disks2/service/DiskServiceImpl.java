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
    public List<Disk> listOwnDisks(Integer userid) {
        return diskDAO.listOwnDisks(userid);
    }
}