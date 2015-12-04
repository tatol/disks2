package disks2.repository;

import disks2.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User as u where u.login=:login and u.password=:password")
    User getUser(@Param("login") String userName,@Param("password") String userPassword);
}
