package com.ymcoffee.dao.hibernate;


import com.ymcoffee.dao.hibernate.base.ExtJpaRepository;
import com.ymcoffee.model.News;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface NewsRepository extends ExtJpaRepository<News, Long> {

	@Query(value = "select * from ym_news where is_deleted = 0 order by seq", nativeQuery = true)
	List<News> findAll();

}
