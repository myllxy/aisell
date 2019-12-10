package cn.itsource.aisell.repository;

import cn.itsource.aisell.domain.Employee;
import cn.itsource.aisell.query.EmployeeQuery;
import com.github.wenhao.jpa.Specifications;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.*;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class EmployeeRepositoryTest {

    @Autowired
    /*BaseRepository<Employee,Long>*/
    private EmployeeRepository employeeRepository;

    //查询所有
    @Test
    public void testFindAll() throws Exception {
        /*org.springframework.data.jpa.repository.support.SimpleJpaRepository@54562ea6*/
        System.out.println("===" + employeeRepository);
        /*class com.sun.proxy.$Proxy30*/
        System.out.println("===" + employeeRepository.getClass());
        List<Employee> list = employeeRepository.findAll();
        list.forEach(System.out::println);

    }

    //查询一个
    @Test
    public void testFindOne() throws Exception {
        Employee employee = employeeRepository.findOne(1L);
        System.out.println(employee);
//        Employee one = employeeRepository.getOne(1L);
//        System.out.println(one);
    }

    //添加一个
    @Test
    public void testSave() throws Exception {
        Employee employee = new Employee();
        employee.setUsername("GG哥");

        employeeRepository.save(employee);
    }

    //修改一个
    @Test
    public void testUpdate() throws Exception {
        Employee employee = new Employee();
        employee.setId(274L);
        employee.setUsername("GG大哥");
        employee.setAge(45);
        //自动帮你确认是添加还是修改
        employeeRepository.save(employee);
    }

    //删除一个
    @Test
    public void testDelete() throws Exception {
        employeeRepository.delete(267L);
    }

    //分页功能
    @Test
    public void testPage() throws Exception {
        int pagebegin = 0;
        int size = 10;
        Pageable pageable = new PageRequest(pagebegin, size);
        Page<Employee> page = employeeRepository.findAll(pageable);// 根据参数传入一个分页对象
        while (page.getContent().size() > (page.getTotalElements() / page.getTotalPages())) {
            pageable = new PageRequest(pagebegin++, size);
            page = employeeRepository.findAll(pageable);// 根据参数传入一个分页对象
            System.out.println(page.getTotalElements()); //总条数
            System.out.println(page.getTotalPages()); //总页数
            System.out.println(page.getContent().size()); // 当前页有多少条
            page.forEach(System.out::println);
            System.out.println("=============现在是第" + (pagebegin - 1) + "页");
        }
    }

    //排序功能
    @Test
    public void testSort() throws Exception {
        /*
          第一个参数:排序方式
          第二个参数:排序属性
         */
        Sort sort = new Sort(Sort.Direction.DESC, "age");
        List<Employee> list = employeeRepository.findAll(sort);
        list.forEach(System.out::println);
    }

    //分页排序
    @Test
    public void testPageSort() throws Exception {
        //创建排序对象
        Sort sort = new Sort(Sort.Direction.DESC, "age");
        int pagebegin = 0;
        int size = 10;
        Pageable pageable = new PageRequest(pagebegin, size, sort);
        Page<Employee> page = employeeRepository.findAll(pageable);// 根据参数传入一个分页对象
        while (page.getContent().size() > (page.getTotalElements() / page.getTotalPages())) {
            pageable = new PageRequest(pagebegin++, size, sort);
            page = employeeRepository.findAll(pageable);// 根据参数传入一个分页对象
            System.out.println(page.getTotalElements()); //总条数
            System.out.println(page.getTotalPages()); //总页数
            System.out.println(page.getContent().size()); // 当前页有多少条
            page.forEach(System.out::println);
            System.out.println("=============现在是第" + (pagebegin - 1) + "页");
        }
    }


    /**
     * 根据用户名查询(规范名)
     */
    @Test
    public void testFindByName01() throws Exception {
        Employee employee = employeeRepository.findByUsername("GG大哥");
        System.out.println(employee);
    }

    /**
     * 根据用户名模糊查询(规范名)
     */
    @Test
    public void testFindByName02() throws Exception {
        List<Employee> list = employeeRepository.findByUsernameLike("%1%");
        list.forEach(System.out::println);
    }

    /**
     * 根据用户名和邮件模糊查询(规范名)
     */
    @Test
    public void testFindByName03() throws Exception {
        List<Employee> list =
                employeeRepository.findByUsernameLikeAndEmailLike("%1%", "%2%");
        list.forEach(System.out::println);
    }

    @Test
    public void testQuery01() throws Exception {
        Employee employee = employeeRepository.query01("admin");
        System.out.println(employee);
    }

    @Test
    public void testQuery02() throws Exception {
        List<Employee> list = employeeRepository.query02("%1%");
        list.forEach(System.out::println);
    }

    @Test
    public void testQuery03() throws Exception {
        List<Employee> list = employeeRepository.query03("%1%", "%2%");
        list.forEach(System.out::println);
    }

    @Test
    public void testQuery04() throws Exception {
        List<Employee> list = employeeRepository.query04();
        list.forEach(System.out::println);
    }


    /**
     * 根据用户名进行模糊查询  select ... from ... where username like ?
     *
     * @throws Exception
     */
    @Test
    public void testJpaSpecificationExecutor01() throws Exception {
        /**
         * Predicate : 使以…为依据;  where 条件 and 条件 ....
         * @param root : 根代表了可以查询和操作的实体对象的根，
         *             帮咱们获取字段(username,password,email)
         * @param cq : 代表一个specific的顶层查询对象
         *              查询哪些字段，排序是什么(主要是把多个查询的条件连系起来)
         * @param cb 用来构建CriteriaQuery的构建器对象(相当于条件或者说条件组合)
         *                主要判断关系（和这个字段是相等，大于，小于like等）
         */
        List<Employee> list = employeeRepository.findAll(new Specification<Employee>() {
            @Override
            public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                //1.拿到用户名(路径)   Path<X> extends Expression
                Path usernamePath = root.get("username");
                System.out.println("======" + usernamePath.getClass());
                //2.用户名进行判断(like) username like ?
                Predicate predicate = cb.like(usernamePath, "%1%");
                return predicate;
            }
        });
        list.forEach(System.out::println);
    }


    //根据用户名和邮件进行查询
    @Test
    public void testJpaSpecificationExecutor02() throws Exception {
        /**
         * 根据规则进行查询：Specification
         */
        List<Employee> list = employeeRepository.findAll(new Specification<Employee>() {
            @Override
            public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                //1.拿到用户名
                Path usernamePath = root.get("username");
                //2.拿到邮件
                Path emailPath = root.get("email");
                //3.拿到年龄
                Path agePath = root.get("age");
                //拿到用户名条件
                Predicate p1 = cb.like(usernamePath, "%1%");
                //拿到邮件的条件
                Predicate p2 = cb.like(emailPath, "%2%");
                //拿到年龄的条件
                Predicate p3 = cb.gt(agePath, 20);
                //把两个条件结合起来
                Predicate predicate = cb.and(p1, p2, p3);
                return predicate;
            }
        });
        list.forEach(System.out::println);
    }


    /**
     * 查询+分页
     */
    @Test
    public void testJpaSpecificationExecutorPage() throws Exception {
        Pageable pageable = new PageRequest(0, 5);//创建分页对象
        /*根据规则进行查询：Specification*/
        Page<Employee> list = employeeRepository
                .findAll((Root<Employee> root, CriteriaQuery<?> cq, CriteriaBuilder cb) -> {
                    Path usernamePath = root.get("username");
                    return cb.like(usernamePath, "%1%");
                }, pageable);
        list.forEach(System.out::println);
    }


    /**
     * 查询+分页+排序
     */
    @Test
    public void testJpaSpecificationExecutorPageSort() throws Exception {
        Sort sort = new Sort(Sort.Direction.DESC, "age");
        int pagebegin = 0;
        int size = 10;
        //创建分页对象
        Pageable pageable = new PageRequest(pagebegin, size, sort);
        /*根据规则进行查询：Specification*/
        Specification<Employee> spec = (Root<Employee> root, CriteriaQuery<?> cq, CriteriaBuilder cb) -> {
            Path usernamePath = root.get("username");
            return cb.like(usernamePath, "%1%");
        };
        Page<Employee> list = employeeRepository.findAll(spec, pageable);
        while (list.getContent().size() > (list.getTotalElements() / list.getTotalPages())) {
            pageable = new PageRequest(pagebegin++, size, sort);
            list = employeeRepository.findAll(spec, pageable);
            list.forEach(System.out::println);
            System.out.println("=============以上是第" + (pagebegin - 1) + "页");
        }
    }


    /**
     * 根据用户名进行模糊查询
     *
     * @throws Exception
     */
    @Test
    public void testJpaSpec01() throws Exception {
        Specification<Employee> spec = Specifications.<Employee>and()
                .like("username", "%1%")
                .like("email", "%2%")
                .gt("age", 20)
                .build();
        List<Employee> list = employeeRepository.findAll(spec);
        list.forEach(System.out::println);
    }


    /**
     * 分页+排序+条件查询
     *
     * @throws Exception
     */
    @Test
    public void testJpaSpec02() throws Exception {
        //1.创建排序对象
        Sort sort = new Sort(Sort.Direction.DESC, "age");
        //2.创建分页对象
        Pageable pageable = new PageRequest(0, 5, sort);
        //3.创建查询对象
        Specification<Employee> spec = Specifications.<Employee>and()
                .like("username", "%1%")
                .build();
        //查询数据
        Page<Employee> page = employeeRepository.findAll(spec, pageable);
        page.forEach(System.out::println);
    }


    /**
     * 分页+排序+条件查询
     *
     * @throws Exception
     */
    @Test
    public void testJpaSpec03() throws Exception {
        //模拟前台传过来的数据
        //设置查询对象
        EmployeeQuery query = new EmployeeQuery();
        query.setUsername("1");
        query.setEmail("2");
        query.setAge(20);
        //设置排序对象
        query.setOrderName("age");
        query.setOrderType(true);
        //调用BaseQuery中的排序对象生成方法
        Sort sort = query.createSort();
        Pageable pageable = new PageRequest(query.getJpaPage(), query.getPageSize(), sort);
        //调用子类中的实现方法来生成查询规则
        Specification<Employee> spec = query.createSpec();
        Page<Employee> page = employeeRepository.findAll(spec, pageable);
        page.forEach(System.out::println);
    }
}
