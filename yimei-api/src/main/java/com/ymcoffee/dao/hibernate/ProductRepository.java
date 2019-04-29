package com.ymcoffee.dao.hibernate;


import com.ymcoffee.dao.hibernate.base.ExtJpaRepository;
import com.ymcoffee.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;


public interface ProductRepository extends ExtJpaRepository<Product, Long> {

    Page<Product> findAll(Specification<Product> spec, Pageable pageable);

}
