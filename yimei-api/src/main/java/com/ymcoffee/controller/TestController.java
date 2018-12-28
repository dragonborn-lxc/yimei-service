package com.ymcoffee.controller;

import com.ymcoffee.dao.hibernate.UserRepository;
import com.ymcoffee.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/test")
    public String test() {
        return "test";
    }
}
