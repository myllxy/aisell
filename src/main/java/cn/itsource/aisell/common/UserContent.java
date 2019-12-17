package cn.itsource.aisell.common;

import cn.itsource.aisell.domain.Employee;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * @author myllxy
 * @create 2019-12-17 13:26
 */
public class UserContent {
    public static Employee getEmp() {
        /* 在这里获得登录后的主体 */
        /* 只要是登录成功后我都能拿到这个主体 */
        Subject subject = SecurityUtils.getSubject();
        /* 如果要转型这里必须相同,不然会报转型错误 */
        /* new SimpleAuthenticationInfo(employee, employee.getPassword(), salt, "myRealm"); */
        return (Employee) subject.getPrincipal();
    }
}
