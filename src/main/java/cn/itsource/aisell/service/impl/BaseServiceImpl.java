package cn.itsource.aisell.service.impl;

import cn.itsource.aisell.query.BaseQuery;
import cn.itsource.aisell.repository.BaseRepository;
import cn.itsource.aisell.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

/**
 * 父接口的实现  这个类就是一个父类,根本就不用它创建对象
 * @param <T>
 * @param <ID>
 */
@Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
public abstract class BaseServiceImpl<T,ID extends Serializable> implements IBaseService<T,ID> {

    /**
     * 这个地方咱们注入的是BaseRepository的实现
     *      其实对应的是它的所有子接口的实现(注:它有很多子接口)
     *      EmployeeRepository
     *      DepartmentRepository
     */
    @Autowired
    private BaseRepository<T,ID> baseRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(T t) {
        baseRepository.save(t);
    }

    @Override
    @Transactional
    public void delete(ID id) {
        baseRepository.delete(id);
    }

    @Override
    public T findOne(ID id) {
        return baseRepository.findOne(id);
    }

    @Override
    public List<T> findAll() {
        return baseRepository.findAll();
    }

    @Override
    public List<T> queryAll(BaseQuery query) {
        //1.拿到查询规则
        Specification spec = query.createSpec();
        //2.进行查询
        return baseRepository.findAll(spec);
    }

    @Override
    public Page<T> queryPage(BaseQuery query) {
        //1.拿到排序对象
        Sort sort = query.createSort();
        //2.拿到分页对象
        Pageable pageable = new PageRequest(query.getJpaPage(),query.getPageSize(),sort);
        //3.拿到查询规则
        Specification spec = query.createSpec();
        //4.进行查询
        return baseRepository.findAll(spec,pageable);
    }

    @Override
    public List<Object> queryByJpql(String jpql, Object... params) {
        //1.创建query对象
        Query query = entityManager.createQuery(jpql);
        //2.把参数放进去
        for (int i = 0; i < params.length; i++) {
            query.setParameter(i+1,params[i]);
        }
        //3.获到数据
        return query.getResultList();
    }
}
