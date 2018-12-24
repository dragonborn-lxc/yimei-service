package com.ymcoffee.controller;

import com.ymcoffee.mapper.entity.YmRecommendType;
import com.ymcoffee.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/all")
    public List<YmRecommendType> test() {
        return testService.queryAll();
    }

}
