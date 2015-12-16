package disks2.repository;

import disks2.domain.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by adminvl on 15.12.2015.
 */
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
}
