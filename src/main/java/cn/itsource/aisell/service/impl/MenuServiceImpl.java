package cn.itsource.aisell.service.impl;

import cn.itsource.aisell.common.UserContent;
import cn.itsource.aisell.domain.Employee;
import cn.itsource.aisell.domain.Menu;
import cn.itsource.aisell.repository.MenuRepository;
import cn.itsource.aisell.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl extends BaseServiceImpl<Menu, Long> implements IMenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Override
    public List<Menu> findParentMenus() {
        //1.创建一个装父菜单的容器
        List<Menu> parentMenus = new ArrayList<>();
        //2.拿到这个用户的菜单
        Employee loginUser = UserContent.getEmp();
        List<Menu> menus = menuRepository.findMenusByUser(loginUser.getId());
        //3.遍历子菜单
        menus.forEach(m -> {
            // 3.1 通过子菜单获取父菜单
            Menu parentMenu = m.getParent();
            // 3.2 判断集合中是否有这个父菜单
            if (!parentMenus.contains(parentMenu)) {
                //没有的话，就把它放进去
                parentMenus.add(parentMenu);
            }
            parentMenu.getChildren().add(m);
        });

        return parentMenus;
    }
}
