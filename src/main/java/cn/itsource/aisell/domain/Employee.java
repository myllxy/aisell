package cn.itsource.aisell.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "employee")
public class Employee extends BaseDomain {
    private String username;
    private String password;
    private String email;
    private Integer age;
    private String headImage;
    @ManyToOne(fetch = FetchType.LAZY)
    /* jpa为了做懒加载，其中的一些属性无法被序列化 */
    /* employee独享最终会转换成json格式发送给前台 */
    /* 我们将这些属性用这个注解将其排除在这个过程中 */
    // @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
    @JoinColumn(name = "department_id")
    /* 经过jpa之后department已经成为一个代理对象 */
    private Department department;

    @Override
    public String toString() {
        return "Employee{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", headImage='" + headImage + '\'' +
                ", department=" + department +
                '}';
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Employee(String username, String password, String email, Integer age, String headImage, Department department) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.age = age;
        this.headImage = headImage;
        this.department = department;
    }

    public String getHeadImage() {
        return headImage;
    }

    public Employee() {
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Employee(String username, String password, String email, Integer age, String headImage) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.age = age;
        this.headImage = headImage;
    }

}
