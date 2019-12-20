package cn.itsource.aisell.service;

import cn.itsource.aisell.domain.Employee;

/**
 * 这里一句代码都没有，但是以后会有很多自己的
 */
public interface IEmployeeService extends IBaseService<Employee, Long> {
    Employee findByUsername(String username);
    Employee findFirstByUsername(String username);
}
