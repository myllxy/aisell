package cn.itsource.aisell.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author myllxy
 * @create 2019-12-21 10:49
 */
@Entity
@Table(name = "Systemdictionarydetail")
public class Systemdictionarydetail extends BaseDomain {
    private String name;
    @ManyToOne
    @JoinColumn(name = "types_id")
    private Systemdictionarytype sdt;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Systemdictionarytype getSdt() {
        return sdt;
    }

    public void setSdt(Systemdictionarytype sdt) {
        this.sdt = sdt;
    }

    public Systemdictionarydetail() {

    }

    public Systemdictionarydetail(String name, Systemdictionarytype sdt) {

        this.name = name;
        this.sdt = sdt;
    }
}
