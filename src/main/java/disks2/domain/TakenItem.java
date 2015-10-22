package disks2.domain;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by adminvl on 22.10.2015.
 */
@Entity
@Table(name = "takenitem")
public class TakenItem implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "diskid")
    private Disk disk;
    @ManyToOne
    @JoinColumn(name= "fromid")
    private User fromUser;
    @ManyToOne
    @JoinColumn(name= "userid")
    private User user;

    public Disk getDisk() {
        return disk;
    }

    public void setDisk(Disk disk) {
        this.disk = disk;
    }

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}