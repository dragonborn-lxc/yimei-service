package com.ymcoffee.dao.hibernate;


import com.ymcoffee.dao.hibernate.base.ExtJpaRepository;
import com.ymcoffee.model.RecommendProduct;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface RecommendProductRepository extends ExtJpaRepository<RecommendProduct, Long> {

	@Query(value = "select * from ym_recommend_product where is_deleted = 0 order by create_time", nativeQuery = true)
	List<RecommendProduct> findAll();

}
