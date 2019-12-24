package cn.itsource.aisell.query;

import cn.itsource.aisell.domain.Purchasebill;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.wenhao.jpa.Specifications;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


/**
 * 接收员工查询的特殊条件
 */
public class PurchasebillQuery extends BaseQuery {
    private Date begindate;
    private Date enddate;
    private Integer status;

    public PurchasebillQuery() {
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public PurchasebillQuery(Date begindate, Date enddate, Integer status) {

        this.begindate = begindate;
        this.enddate = enddate;
        this.status = status;
    }

    public Date getBegindate() {
        return begindate;
    }

    /**
     * 查询的时候是查年月日这个范围,所以这里写年月日就好了
     *
     * @param begindate
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public void setBegindate(Date begindate) {
        this.begindate = begindate;
    }

    public Date getEnddate() {
        return enddate;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    /**
     * 返回咱们的查询规则(查询条件都在这个类中)
     *
     * @return
     */
    @Override
    public Specification createSpec() {
        if (enddate != null) {
            enddate = DateUtils.addDays(enddate, 1);
        }
        Specification<Purchasebill> spec = Specifications.<Purchasebill>and()
                .ge(begindate != null, "vdate", begindate)
                .lt(enddate != null, "vdate", enddate)
                .eq(status != null, "status", status)
                .build();
        return spec;
    }
}
