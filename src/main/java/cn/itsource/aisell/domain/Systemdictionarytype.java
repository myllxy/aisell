package cn.itsource.aisell.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author myllxy
 * @create 2019-12-21 10:55
 */
@Entity
@Table(name = "systemdictionarytype")
public class Systemdictionarytype extends BaseDomain {
    private String sn;
    private String name;

    @Override
    public String toString() {
        return "Systemdictionarytype{" +
                "sn='" + sn + '\'' +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    public Systemdictionarytype() {
    }

    public Systemdictionarytype(String sn, String name) {

        this.sn = sn;
        this.name = name;
    }
}
