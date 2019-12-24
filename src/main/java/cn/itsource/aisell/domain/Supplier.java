package cn.itsource.aisell.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author myllxy
 * @create 2019-12-21 14:44
 */
@Entity
@Table(name = "supplier")
public class Supplier extends BaseDomain {
    private String name;

    public Supplier() {
    }

    public Supplier(String name) {

        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
