package cn.itsource.aisell.service;

import cn.itsource.aisell.SpringTest;
import cn.itsource.aisell.domain.Employee;
import cn.itsource.aisell.query.EmployeeQuery;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

public class EmployeeServiceTest extends SpringTest {

    @Autowired
    private IEmployeeService employeeService;

    @Test
    public void testFindAll() throws Exception{
        employeeService.findAll().forEach(System.out::println);
    }
    
    @Test
    public void testQueryAll() throws Exception{
        EmployeeQuery query = new EmployeeQuery();
        query.setUsername("1");

        Page<Employee> page = employeeService.queryPage(query);
        page.forEach(System.out::println);
    }
}
