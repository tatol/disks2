package disks2.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

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
    @Column(name = "enabled")
    @JsonIgnore
    private boolean enabled;
    @NotEmpty
    @Column(name = "login")
    private String login;
    @NotEmpty
    @JsonIgnore
    @Column(name = "password", length = 60)
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Disk> takenList;

    @JsonIgnore
    @OneToMany(mappedBy = "fromUser", fetch = FetchType.LAZY)
    private List<Disk> givenList;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<UserRole> roles;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

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