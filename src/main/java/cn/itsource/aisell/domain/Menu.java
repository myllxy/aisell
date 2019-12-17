package cn.itsource.aisell.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "menu")
public class Menu extends BaseDomain {

    private String name;
    private String url;
    private String icon;

    /**
     * 多对一，儿子菜单可以找到父亲菜单
     * JsonIgnore:SpringMVC传json就不会使用这个字段了
     */
    @ManyToOne
    @JoinColumn(name = "parent_id")
    @JsonIgnore
    private Menu parent;
    /**
     * 父亲可以找到儿子
     * 不配一对多，但是这个字段必需要有(有这个值才能出树型结构)
     * 这里面的值就由我们自己手动添加
     *
     * @Transient:临时属性 -> domain里有就是会有些字段可以不需要和数据库有关系
     */
    @Transient
    private List<Menu> children = new ArrayList<>();


    public String getName() {
        return name;
    }

    /**
     * 1.方法一：由于easyui的菜单需要"text": "系统管理",
     * 所以这里使用getText来进行匹配
     * 2.方法二：
     *   @Column (name = "name")
     *   private String text;
     * 同样能达到以上的效果
     * @return
     */
    public String getText() {
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    public Menu getParent() {
        return parent;
    }

    public void setParent(Menu parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", icon='" + icon + '\'' +
                ", id=" + id +
                '}';
    }
}
