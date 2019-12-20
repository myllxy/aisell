package cn.itsource.aisell.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employee")
public class Employee extends BaseDomain {
    @Excel(name = "用户名")
    @NotNull
    private String username;
    private String password;
    @Excel(name = "邮箱", width = 20)
    private String email;
    @Excel(name = "年龄")
    @Max(60)
    @Min(18)
    private Integer age;
    /* 设置为2表示存储的是真实的图片而不是路径 */
    @Excel(name = "头像", type = 2, savePath = "/images/head", height = 23)
    private String headImage;
    @ManyToOne(fetch = FetchType.LAZY)
    /* jpa为了做懒加载，其中的一些属性无法被序列化 */
    /* employee独享最终会转换成json格式发送给前台 */
    /* 我们将这些属性用这个注解将其排除在这个过程中 */
    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
    @JoinColumn(name = "department_id")
    /* 经过jpa之后department已经成为一个代理对象 */
    @ExcelEntity
    private Department department;
    @ManyToMany
    @JoinTable(name = "employee_role",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles = new ArrayList<>();


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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", headImage='" + headImage + '\'' +
                ", department=" + department +
                ", roles=" + roles +
                ", id=" + id +
                '}';
    }
}
