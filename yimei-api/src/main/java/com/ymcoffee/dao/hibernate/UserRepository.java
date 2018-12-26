package com.ymcoffee.dao.hibernate;


import com.ymcoffee.dao.hibernate.base.ExtJpaRepository;
import com.ymcoffee.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface UserRepository extends ExtJpaRepository<User, Long> {
    List<User> findAllByMobile(String mobile);

    List<User> findAllByMobileAndPassword(String mobile, String password);
}
