package cn.itsource.aisell.service;

import cn.itsource.aisell.query.BaseQuery;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;

public interface IBaseService<T,ID extends Serializable> {
    //添加或者修改
    void save(T t);
    void delete(ID id);
    T findOne(ID id);
    List<T> findAll();

    //根据查询对象进行查询
    List<T> queryAll(BaseQuery query);
    //根据查询对象拿到分页数据
    Page<T> queryPage(BaseQuery query);
    //根据JPQL进行查询
    /**
     * jpql:语句 select o from Employee where name = ? and age > ?
     * params：参数(类型与数量不确定的)
     */
    List<Object> queryByJpql(String jpql,Object... params);

}
