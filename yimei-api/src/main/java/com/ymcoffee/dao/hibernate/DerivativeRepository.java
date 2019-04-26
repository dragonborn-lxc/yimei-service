package com.ymcoffee.dao.hibernate;


import com.ymcoffee.dao.hibernate.base.ExtJpaRepository;
import com.ymcoffee.model.Derivative;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;


public interface DerivativeRepository extends ExtJpaRepository<Derivative, Long> {

    Page<Derivative> findAll(Specification<Derivative> spec, Pageable pageable);

}
