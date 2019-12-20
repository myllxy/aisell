package cn.itsource.aisell.service.impl;

import cn.itsource.aisell.common.MD5utils;
import cn.itsource.aisell.domain.Employee;
import cn.itsource.aisell.repository.EmployeeRepository;
import cn.itsource.aisell.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EmployeeServiceImpl extends BaseServiceImpl<Employee, Long> implements IEmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public void save(Employee employee) {
        if (employee.getId() == null) {
            employee.setPassword(MD5utils.afterEncrypt(employee.getPassword()));
        }
        super.save(employee);
    }

    @Override
    public Employee findByUsername(String username) {
        return employeeRepository.findByUsername(username);
    }

    @Override
    public Employee findFirstByUsername(String username) {
        return employeeRepository.findFirstByUsername(username);
    }
}
