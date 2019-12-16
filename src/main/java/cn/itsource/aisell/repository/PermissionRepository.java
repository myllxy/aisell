package cn.itsource.aisell.repository;

import cn.itsource.aisell.domain.Permission;

/**
 * 第一个泛型:代表类型
 * 第二个泛型:主键类型
 */
public interface PermissionRepository extends BaseRepository<Permission,Long> {
}
