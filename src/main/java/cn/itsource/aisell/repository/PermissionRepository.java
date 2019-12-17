package cn.itsource.aisell.repository;

import cn.itsource.aisell.domain.Permission;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

/**
 * 第一个泛型:代表类型
 * 第二个泛型:主键类型
 */
public interface PermissionRepository extends BaseRepository<Permission, Long> {
    /* 注意这里p.sn的类型必须和下面Set的数据类型一致，不然会报类型转换错误 */
    @Query("select p.sn from Employee e join e.roles r join r.permissions p where e.id = ?1")
    Set<String> findSnByname(Long userId);
}
