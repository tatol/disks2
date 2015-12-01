package disks2.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by adminvl on 21.10.2015.
 */
@Entity
@Table(name = "user")
public class User implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private Integer id;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Disk> takenList;

    @OneToMany(mappedBy = "fromUser", fetch = FetchType.LAZY)
    private List<Disk> givenList;

    public List<Disk> getGivenList() {
        return givenList;
    }

    public List<Disk> getTakenList() {
        return takenList;
    }

    public void setTakenList(List<Disk> takenList) {
        this.takenList = takenList;
    }

    public void setGivenList(List<Disk> givenList) {
        this.givenList = givenList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}