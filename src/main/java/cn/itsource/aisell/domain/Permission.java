package cn.itsource.aisell.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author myllxy
 * @create 2019-12-16 10:50
 */
@Entity
@Table(name = "permission")
public class Permission extends BaseDomain {

    //权限名称
    private String name;
    //权限资源(路径)
    private String url;
    //描述
    private String descs;
    //编码
    private String sn;
    //menu_id
    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescs() {
        return descs;
    }

    public void setDescs(String descs) {
        this.descs = descs;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public Permission() {

    }

    public Permission(String name, String url, String descs, String sn, Menu menu) {
        this.name = name;
        this.url = url;
        this.descs = descs;
        this.sn = sn;
        this.menu = menu;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}
