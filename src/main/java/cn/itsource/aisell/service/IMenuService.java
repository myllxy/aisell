package cn.itsource.aisell.service;

import cn.itsource.aisell.domain.Menu;

import java.util.List;

/**
 * 这里一句代码都没有，但是以后会有很多自己的
 */
public interface IMenuService extends IBaseService<Menu,Long> {

    List<Menu> findParentMenus();
}
