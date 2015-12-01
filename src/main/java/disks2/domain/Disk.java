package disks2.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by adminvl on 21.10.2015.
 */
@Entity
@Table(name = "disk")
public class Disk implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinTable(name = "takenitem", joinColumns = @JoinColumn(name = "diskid"), inverseJoinColumns = @JoinColumn(name = "userid"))
    private User user;

    @ManyToOne
    @JoinTable(name = "givenitem", joinColumns = @JoinColumn(name = "diskid"), inverseJoinColumns = @JoinColumn(name = "fromid"))
    private User fromUser;

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
