package com.ymcoffee.controller.app;

import com.ymcoffee.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/app/recommend")
@RestController
public class AppRecommendController {

    @Autowired
    private RecommendService recommendService;

    @PostMapping("/banner")
    public List<String> banner() {
        return recommendService.getBannerList();
    }

}
