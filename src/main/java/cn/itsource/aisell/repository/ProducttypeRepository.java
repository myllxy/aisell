package cn.itsource.aisell.repository;

import cn.itsource.aisell.domain.Producttype;

/**
 * 第一个泛型:代表类型
 * 第二个泛型:主键类型
 */
public interface ProducttypeRepository extends BaseRepository<Producttype, Long> {

}
