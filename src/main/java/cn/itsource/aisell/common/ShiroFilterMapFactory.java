package cn.itsource.aisell.common;

import cn.itsource.aisell.domain.Permission;
import cn.itsource.aisell.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author myllxy
 * @create 2019-12-14 19:54
 */
public class ShiroFilterMapFactory {
    @Autowired
    IPermissionService permissionService;

    public Map<String, String> createMap() {
        //准备一个有顺序的map
        Map<String, String> map = new LinkedHashMap<>();
        //添加放选择数据
        map.put("/login", "anon");
        //把所有的静态资源(js,css,图片,...)放行
        map.put("*.js", "anon");
        map.put("*.css", "anon");
        map.put("/css/**", "anon");
        map.put("/js/**", "anon");
        map.put("/easyui/**", "anon");
        map.put("/images/**", "anon");
        //添加权限拦截数据
        List<Permission> permissionList = permissionService.findAll();
        /* shiro中有很多权限拦截器 */
        /* "perms[" + e.getSn() + "]"中的perms就代表一个权限拦截器PermissionsAuthorizationFilter */
        permissionList.forEach(e -> map.put(e.getUrl(), "aisellPerms[" + e.getSn() + "]"));
        //拦截所有
        map.put("/**", "authc");
        return map;
    }
}
