package cn.itsource.aisell.repository;

import cn.itsource.aisell.domain.Department;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 第一个泛型:代表类型
 * 第二个泛型:主键类型
 */
public interface DepartmentRepository extends BaseRepository<Department,Long> {
}
