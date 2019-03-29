package com.ymcoffee.dao.hibernate;


import com.ymcoffee.dao.hibernate.base.ExtJpaRepository;
import com.ymcoffee.model.Banner;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface BannerRepository extends ExtJpaRepository<Banner, Long> {

    @Query(value = "select * from ym_banner where is_deleted = 0 order by seq", nativeQuery = true)
    List<Banner> findAll();

}
