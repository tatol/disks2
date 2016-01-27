package disks2.service;

import disks2.domain.UserRole;
import disks2.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by adminvl on 15.12.2015.
 */
@Service
@Transactional
public class UserRoleServiceImpl implements UserRoleService {
    @Resource
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public void addUserRole(UserRole userRole) {
        userRoleRepository.saveAndFlush(userRole);
    }



}
