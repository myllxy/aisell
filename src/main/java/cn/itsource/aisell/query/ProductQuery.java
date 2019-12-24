package cn.itsource.aisell.query;

import cn.itsource.aisell.domain.Product;
import com.github.wenhao.jpa.Specifications;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

/**
 * 接收员工查询的特殊条件
 */
public class ProductQuery extends BaseQuery {
    public ProductQuery() {
    }

    public ProductQuery(String name, String color) {

        this.name = name;
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    private String name;
    private String color;

    /**
     * 返回咱们的查询规则(查询条件都在这个类中)
     *
     * @return
     */
    @Override
    public Specification createSpec() {
        Specification<Product> spec = Specifications.<Product>and()
                .like(StringUtils.isNotBlank(name), "name", "%" + name + "%")
                .like(StringUtils.isNotBlank(color), "color", "%" + color + "%")
                .build();
        return spec;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
