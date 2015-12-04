package disks2.repository;

import disks2.domain.Disk;
import disks2.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
public interface DiskRepository extends JpaRepository<Disk, Integer> {

    @Query("select d from Disk as d where d.user.id=:id and d.fromUser.id is null")
    List<Disk> listOwnDisks(@Param("id") Integer userId);
    @Query("select d from Disk as d where d.fromUser is null and d.user.id is null")
    List<Disk> listFreeDisks();

    @Query("select u from User as u where u.id=:id")
    User getUserById(@Param("id")Integer userId);
    @Query("select d from Disk as d where d.id=:id")
    Disk getDiskById(@Param("id")Integer userId);
    @Query("select d from Disk as d where d.id=?2 and d.user=?1")
    Disk getDiskByIdAndUser(User user, Integer userId);

    @Modifying
    @Query("update Disk d set d.user = ?1 where d.id = ?2")
    void takeFreeDisk(User user, Integer diskId);
    @Modifying
    @Query("update Disk d set d.user = null where d.id = ?2 and d.user=?1")
    void returnOwnDisk(User user, Integer diskId);
    @Modifying
    @Query("update Disk d set d.user = ?1, d.fromUser = ?3 where d.id = ?2 and d.user=?3")
    void takeFreeDiskFromUser(User user, Integer diskId, User fromUser);
    @Modifying
    @Query("update Disk d set d.user = ?3, d.fromUser = null where d.id = ?2 and d.user=?1")
    void returnDiskToUser(User user, Integer diskId, User fromUser);

    @Query("select d from Disk as d where d.user.id !=:id and d.fromUser.id is null")
    List<Disk> listOwnDisksFromAllUsers(@Param("id") Integer currentUserId);
    @Query("select d from Disk as d where d.user.id=:id and d.fromUser.id is not null")
    List<Disk> listTakenDisksByUser(@Param("id") Integer userId);
    @Query("select d from Disk as d where d.fromUser.id=:id")
    List<Disk> listTakenDisksFromUser(@Param("id")Integer userId);
}

