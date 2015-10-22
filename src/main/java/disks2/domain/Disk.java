package disks2.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

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

    @OneToMany(mappedBy = "disk", fetch = FetchType.LAZY)
    private List<TakenItem> diskList;

    public List<TakenItem> getDiskList() {
        return diskList;
    }

    public void setDiskList(List<TakenItem> diskList) {
        this.diskList = diskList;
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
