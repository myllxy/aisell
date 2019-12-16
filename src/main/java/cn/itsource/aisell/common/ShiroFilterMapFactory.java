package cn.itsource.aisell.common;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author myllxy
 * @create 2019-12-14 19:54
 */
public class ShiroFilterMapFactory {
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
        map.put("/employee/index", "perms[employee:index]");
        map.put("/department/index", "perms[department:index]");
        //拦截所有
        map.put("/**", "authc");
        return map;
    }
}
