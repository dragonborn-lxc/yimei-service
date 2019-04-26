package com.ymcoffee.dao.hibernate;


import com.ymcoffee.dao.hibernate.base.ExtJpaRepository;
import com.ymcoffee.model.Art;
import com.ymcoffee.model.Banner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ArtRepository extends ExtJpaRepository<Art, Long> {

    Page<Art> findAll(Specification<Art> spec, Pageable pageable);

}
