package disks2.repository;

import disks2.domain.User;
import disks2.domain.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User as u where u.login=:login")
    User getUserByName(@Param("login") String userName);
}
