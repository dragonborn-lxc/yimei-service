package com.ymcoffee.dao.hibernate;

import com.ymcoffee.dao.hibernate.base.ExtJpaRepository;
import com.ymcoffee.model.SmsVerify;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface SmsVerifyRepository extends ExtJpaRepository<SmsVerify, Long> {
    @Query(value = "select * from yd_st_sms_verify " +
            "where mobile=?1 and code=?2 " +
            "and TIMESTAMPDIFF(SECOND, create_time, now()) < ?3 " +
            "and valid = true ",
            nativeQuery = true)
    List<SmsVerify> findValid(String mobile, String code, Integer validSeconds);
}
