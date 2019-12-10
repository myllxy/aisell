package cn.itsource.aisell.query;

import cn.itsource.aisell.domain.Employee;
import com.github.wenhao.jpa.Specifications;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

/**
 * 接收员工查询的特殊条件
 */
public class EmployeeQuery extends BaseQuery {

    private String username;
    private String email;
    private Integer age;

    /**
     * 返回咱们的查询规则(查询条件都在这个类中)
     * @return
     */
    @Override
    public Specification createSpec(){
        Specification<Employee> spec = Specifications.<Employee>and()
                .like(StringUtils.isNotBlank(username), "username", "%" + username + "%")
                .like(StringUtils.isNotBlank(email), "email", "%" + email + "%")
                .gt(age != null, "age", age)
                .build();
        return spec;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
