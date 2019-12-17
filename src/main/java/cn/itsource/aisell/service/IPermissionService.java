package cn.itsource.aisell.service;

import cn.itsource.aisell.domain.Permission;
import cn.itsource.aisell.domain.Role;

import java.util.Set;

/**
 * 这里一句代码都没有，但是以后会有很多自己的
 */
public interface IPermissionService extends IBaseService<Permission, Long> {
    Set<String> findSnByname();
}
