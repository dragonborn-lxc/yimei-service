package com.ymcoffee.dao.hibernate;


import com.ymcoffee.dao.hibernate.base.ExtJpaRepository;
import com.ymcoffee.model.Dictionary;

import java.util.List;


public interface DictionaryRepository extends ExtJpaRepository<Dictionary, Long> {

    List<Dictionary> findAllByCodeOrderBySubcode(int code);

}
