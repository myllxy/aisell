package cn.itsource.aisell.service.impl;

import cn.itsource.aisell.common.UserContent;
import cn.itsource.aisell.domain.Employee;
import cn.itsource.aisell.domain.Permission;
import cn.itsource.aisell.domain.Role;
import cn.itsource.aisell.repository.PermissionRepository;
import cn.itsource.aisell.service.IPermissionService;
import cn.itsource.aisell.service.IRoleService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
public class PermissionServiceImpl extends BaseServiceImpl<Permission, Long> implements IPermissionService {
    @Autowired
    PermissionRepository permissionRepository;

    @Override
    public Set<String> findSnByname() {
        Employee employee = UserContent.getEmp();
        Set<String> snByUsername = permissionRepository.findSnByname(employee.getId());
        System.out.println(snByUsername);
        return snByUsername;
    }
}
