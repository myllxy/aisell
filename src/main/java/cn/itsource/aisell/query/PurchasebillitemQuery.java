package cn.itsource.aisell.query;

import cn.itsource.aisell.domain.Product;
import com.github.wenhao.jpa.Specifications;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;


/**
 * 接收员工查询的特殊条件
 */
public class PurchasebillitemQuery extends BaseQuery {
    private String descs; //描述

    public PurchasebillitemQuery() {
    }

    public String getDescs() {
        return descs;
    }

    public PurchasebillitemQuery(String descs) {
        this.descs = descs;
    }

    public void setDescs(String descs) {
        this.descs = descs;
    }

    /**
     * 返回咱们的查询规则(查询条件都在这个类中)
     *
     * @return
     */
    @Override
    public Specification createSpec() {
        Specification<Product> spec = Specifications.<Product>and()
                .like(StringUtils.isNotBlank(descs), "descs", "%" + descs + "%")
                .build();
        return spec;
    }
}
