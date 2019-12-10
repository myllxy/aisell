package cn.itsource.aisell.repository;

import cn.itsource.aisell.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 第一个泛型:代表类型
 * 第二个泛型:主键类型
 */
public interface EmployeeRepository extends BaseRepository<Employee,Long> {

    @Query("select o from Employee o where o.username = ?1")
    Employee query01(String username);
    @Query("select o from Employee o where o.username like ?1")
    List<Employee> query02(String username);
    @Query("select o from Employee o where o.username like ?1 and o.email like ?2")
    List<Employee> query03(String username,String email);
//    @Query("select o from Employee o where o.username like :username and o.email like :email")
//    List<Employee> query03(@Param("username") String username, @Param("email")String email);
    //原生SQL的使用
    @Query(nativeQuery = true,value = "select * from employee")
    List<Employee> query04();
    //根据用户名查询数据
    Employee findByUsername(String username);
    //根据用户名做模糊查询
    List<Employee> findByUsernameLike(String username);
    //根据用户名与邮件进行模糊查询
    List<Employee> findByUsernameLikeAndEmailLike(String username,String email);
}
