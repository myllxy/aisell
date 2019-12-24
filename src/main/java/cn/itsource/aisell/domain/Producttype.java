package cn.itsource.aisell.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author myllxy
 * @create 2019-12-21 10:46
 */
@Entity
@Table(name = "producttype")
public class Producttype extends BaseDomain {
    private String name;
    private String descs;
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Producttype producttype;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescs() {
        return descs;
    }

    public void setDescs(String descs) {
        this.descs = descs;
    }

    public Producttype getProducttype() {
        return producttype;
    }

    public void setProducttype(Producttype producttype) {
        this.producttype = producttype;
    }

    public Producttype() {
    }

    public Producttype(String name, String descs, Producttype producttype) {

        this.name = name;
        this.descs = descs;
        this.producttype = producttype;
    }
}
