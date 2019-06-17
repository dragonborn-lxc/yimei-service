package com.ymcoffee.dao.hibernate;


import com.ymcoffee.dao.hibernate.base.ExtJpaRepository;
import com.ymcoffee.model.Collect;

import javax.transaction.Transactional;
import java.util.List;


public interface CollectRepository extends ExtJpaRepository<Collect, Long> {

	@Transactional
	void deleteByUserIdAndProdId(long userId, long prodId);

	List<Collect> findAllByUserIdAndProdId(long userId, long prodId);

	Long countByUserId(long userId);
}
