package com.ymcoffee.dao.hibernate;


import com.ymcoffee.dao.hibernate.base.ExtJpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface BannerRepository extends ExtJpaRepository<String, Long> {

    @Query("select url from ym_banner where is_deleted = 0 order by seq")
    List<String> findAll();

}
