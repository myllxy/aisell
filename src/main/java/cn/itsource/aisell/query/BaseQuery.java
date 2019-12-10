package cn.itsource.aisell.query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

/**
 * 公共的一些查询条件
 * 父类: 1.公共的代码  2.规范代码
 *      哪怕这里没有代码，你也最好搞一个父类！！ =>  扩展性强
 */
public abstract class BaseQuery {

    //当前页
    private int currentPage = 1;
    //每页条数
    private int pageSize = 10;

    //排序字段(username,age,salary,...)
    private String orderName;
    //排序类型 (true = DESC/ false = ASC)
    private boolean orderType = false;

    //完成排序的对象创建
    public Sort createSort(){
        if(StringUtils.isNotBlank(orderName)){
            //如果有排序字段，我们才返回排序对象
            Sort sort = new Sort(orderType?Sort.Direction.DESC:Sort.Direction.ASC,orderName);
            return sort;
        }
        return null;
    }

    /**
     * 让所有子类都必需实现这个方法
     * @return
     */
    public abstract Specification createSpec();

    public int getCurrentPage() {
        return currentPage;
    }
    //兼容JPA从0开始算页的功能
    public int getJpaPage() {
        return currentPage-1;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
    //兼容easyui的方法
    public void setPage(int page) {
        this.currentPage = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    //兼容easyui的方法
    public void setRows(int rows) {
        this.pageSize = rows;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public boolean isOrderType() {
        return orderType;
    }

    public void setOrderType(boolean orderType) {
        this.orderType = orderType;
    }
}
