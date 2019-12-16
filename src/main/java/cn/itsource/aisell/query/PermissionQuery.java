package cn.itsource.aisell.query;

import cn.itsource.aisell.domain.Employee;
import com.github.wenhao.jpa.Specifications;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

/**
 * 接收员工查询的特殊条件
 */
public class PermissionQuery extends BaseQuery {

    //权限名称
    private String name;
    //权限资源(路径)
    private String url;
    //描述
    private String descs;
    //编码
    private String sn;

    /**
     * 返回咱们的查询规则(查询条件都在这个类中)
     *
     * @return
     */
    @Override
    public Specification createSpec() {
        Specification<Employee> spec = Specifications.<Employee>and()
                .like(StringUtils.isNotBlank(name), "name", "%" + name + "%")
                .like(StringUtils.isNotBlank(url), "url", "%" + url + "%")
                .like(StringUtils.isNotBlank(sn), "sn", "%" + sn + "%")
                .build();
        return spec;
    }


    public PermissionQuery() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public PermissionQuery(String name, String url, String descs, String sn) {
        this.name = name;
        this.url = url;
        this.descs = descs;
        this.sn = sn;
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
}
