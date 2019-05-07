package com.ymcoffee.dao.hibernate;


import com.ymcoffee.dao.hibernate.base.ExtJpaRepository;
import com.ymcoffee.model.DetailImage;

import java.util.List;


public interface DetailImageRepository extends ExtJpaRepository<DetailImage, Long> {

	List<DetailImage> findAllByProdId(long prodId);

}
