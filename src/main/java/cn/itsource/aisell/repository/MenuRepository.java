package cn.itsource.aisell.repository;

import cn.itsource.aisell.domain.Menu;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 第一个泛型:代表类型
 * 第二个泛型:主键类型
 */
public interface MenuRepository extends BaseRepository<Menu, Long> {

    @Query("select distinct m from Employee e join e.roles r join r.permissions p join p.menu m where e.id=?1")
    List<Menu> findMenusByUser(Long userId);


    @Query("select o from Menu o where o.url is null")
    List<Menu> findParentMenus();
}
