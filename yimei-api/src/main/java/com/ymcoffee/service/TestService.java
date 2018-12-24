package com.ymcoffee.service;

import com.ymcoffee.mapper.YmRecommendTypeMapper;
import com.ymcoffee.mapper.entity.YmRecommendType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    @Autowired
    private YmRecommendTypeMapper ymRecommendTypeMapper;

    public List<YmRecommendType> queryAll() {
        return ymRecommendTypeMapper.queryAll();
    }

}
