package cn.itsource.aisell.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author myllxy
 * @create 2019-12-21 14:42
 */
@Entity
@Table(name = "purchasebillitem")
public class Purchasebillitem extends BaseDomain {
    private BigDecimal price; //价格(单价)
    private BigDecimal num; //数量
    private BigDecimal amount; //小计 = 价格*数量
    private String descs; //描述
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id")
    private Product product; //多对一,非空 产品
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "bill_id")
    @JsonIgnore
    private Purchasebill purchasebill;//组合关系,非空 采购字段


    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getNum() {
        return num;
    }

    public void setNum(BigDecimal num) {
        this.num = num;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescs() {
        return descs;
    }

    public void setDescs(String descs) {
        this.descs = descs;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Purchasebill getPurchasebill() {
        return purchasebill;
    }

    public void setPurchasebill(Purchasebill purchasebill) {
        this.purchasebill = purchasebill;
    }

    public Purchasebillitem() {

    }

    public Purchasebillitem(BigDecimal price, BigDecimal num, BigDecimal amount, String descs, Product product, Purchasebill purchasebill) {

        this.price = price;
        this.num = num;
        this.amount = amount;
        this.descs = descs;
        this.product = product;
        this.purchasebill = purchasebill;
    }
}
