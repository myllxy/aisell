package cn.itsource.aisell.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @author myllxy
 * @create 2019-12-21 10:34
 */
@Entity
@Table(name = "product")
public class Product extends BaseDomain {
    private String name;
    private String color;
    private String pic;
    private String smallPic;
    private BigDecimal costPrice;
    private BigDecimal salePrice;
    @ManyToOne
    @JoinColumn(name = "types_id")
    private Producttype producttype;
    @ManyToOne
    @JoinColumn(name = "unit_id")
    private Systemdictionarydetail sdd1;
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Systemdictionarydetail sdd2;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getSmallPic() {
        return smallPic;
    }

    public void setSmallPic(String smallPic) {
        this.smallPic = smallPic;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public Producttype getProducttype() {
        return producttype;
    }

    public void setProducttype(Producttype producttype) {
        this.producttype = producttype;
    }

    public Systemdictionarydetail getSdd1() {
        return sdd1;
    }

    public void setSdd1(Systemdictionarydetail sdd1) {
        this.sdd1 = sdd1;
    }

    public Systemdictionarydetail getSdd2() {
        return sdd2;
    }

    public void setSdd2(Systemdictionarydetail sdd2) {
        this.sdd2 = sdd2;
    }

    public Product() {

    }

    public Product(String name, String color, String pic, String smallPic, BigDecimal costPrice, BigDecimal salePrice, Producttype producttype, Systemdictionarydetail sdd1, Systemdictionarydetail sdd2) {

        this.name = name;
        this.color = color;
        this.pic = pic;
        this.smallPic = smallPic;
        this.costPrice = costPrice;
        this.salePrice = salePrice;
        this.producttype = producttype;
        this.sdd1 = sdd1;
        this.sdd2 = sdd2;
    }
}
