package com.ymcoffee.dao.hibernate;


import com.ymcoffee.dao.hibernate.base.ExtJpaRepository;
import com.ymcoffee.model.Subject;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface SubjectRepository extends ExtJpaRepository<Subject, Long> {

	@Query(value = "select * from ym_subject where is_deleted = 0 order by seq", nativeQuery = true)
	List<Subject> findAll();

}
