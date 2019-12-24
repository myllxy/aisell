package cn.itsource.aisell.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author myllxy
 * @create 2019-12-21 14:39
 */
@Entity
@Table(name = "purchasebill")
public class Purchasebill extends BaseDomain {
    private Date vdate; // 交易时间
    private BigDecimal totalAmount; //总金额
    private BigDecimal totalNum; //总数量
    private Date inputTime;  //录入时间
    private Date auditorTime; //审核时间
    private Integer status = 0; // 审核状态
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier; // 供货商
    @ManyToOne
    @JoinColumn(name = "auditor_id")
    private Employee auditor;
    @ManyToOne
    @JoinColumn(name = "inputUser_id")
    private Employee inputUser; // 录入员
    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private Employee buyer; // 采购员
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "purchasebill", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Purchasebillitem> list = new ArrayList<Purchasebillitem>();

    public Employee getAuditor() {
        return auditor;
    }

    public void setAuditor(Employee auditor) {
        this.auditor = auditor;
    }

    public Employee getInputUser() {
        return inputUser;
    }

    public void setInputUser(Employee inputUser) {
        this.inputUser = inputUser;
    }

    public Employee getBuyer() {
        return buyer;
    }

    public void setBuyer(Employee buyer) {
        this.buyer = buyer;
    }


    public Purchasebill() {
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getVdate() {
        return vdate;
    }
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    public Purchasebill(Date vdate, BigDecimal totalAmount, BigDecimal totalNum, Date inputTime, Date auditorTime, Integer status, Supplier supplier, Employee auditor, Employee inputUser, Employee buyer) {
        this.vdate = vdate;
        this.totalAmount = totalAmount;
        this.totalNum = totalNum;
        this.inputTime = inputTime;
        this.auditorTime = auditorTime;
        this.status = status;
        this.supplier = supplier;
        this.auditor = auditor;
        this.inputUser = inputUser;
        this.buyer = buyer;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(BigDecimal totalNum) {
        this.totalNum = totalNum;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getInputTime() {
        return inputTime;
    }

    public void setInputTime(Date inputTime) {
        this.inputTime = inputTime;
    }

    public Date getAuditorTime() {
        return auditorTime;
    }

    public void setAuditorTime(Date auditorTime) {
        this.auditorTime = auditorTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

}
