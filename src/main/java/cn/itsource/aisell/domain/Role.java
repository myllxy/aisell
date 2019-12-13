package cn.itsource.aisell.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role extends BaseDomain {
    private String name;
    private String sn;

    public Role() {
    }

    @Override
    public String toString() {
        return "Role{" +
                "name='" + name + '\'' +
                ", sn='" + sn + '\'' +
                ", id=" + id +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public Role(String name, String sn) {
        this.name = name;
        this.sn = sn;
    }
}
