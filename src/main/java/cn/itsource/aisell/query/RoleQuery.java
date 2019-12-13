package cn.itsource.aisell.query;

import cn.itsource.aisell.domain.Employee;
import com.github.wenhao.jpa.Specifications;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

/**
 * 接收员工查询的特殊条件
 */
public class RoleQuery extends BaseQuery {

    private String name;
    private String sn;

    /**
     * 返回咱们的查询规则(查询条件都在这个类中)
     *
     * @return
     */
    @Override
    public Specification createSpec() {
        Specification<Employee> spec = Specifications.<Employee>and()
                .like(StringUtils.isNotBlank(name), "name", "%" + name + "%")
                .like(StringUtils.isNotBlank(sn), "email", "%" + sn + "%")
                .build();
        return spec;
    }


    public RoleQuery() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RoleQuery(String name, String sn) {
        this.name = name;
        this.sn = sn;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    @Override
    public String toString() {
        return "RoleQuery{" +
                "name='" + name + '\'' +
                ", sn=" + sn +
                '}';
    }
}
