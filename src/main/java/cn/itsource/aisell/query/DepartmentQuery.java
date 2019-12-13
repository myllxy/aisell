package cn.itsource.aisell.query;

import cn.itsource.aisell.domain.Department;
import com.github.wenhao.jpa.Specifications;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

/**
 * 接收员工查询的特殊条件
 */
public class DepartmentQuery extends BaseQuery {

    private String name;

    /**
     * 返回咱们的查询规则(查询条件都在这个类中)
     * @return
     */
    @Override
    public Specification createSpec(){
        Specification<Department> spec = Specifications.<Department>and()
                .like(StringUtils.isNotBlank(name), "name", "%" + name + "%")
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
