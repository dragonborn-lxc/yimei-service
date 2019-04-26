package com.ymcoffee.controller.app;

import com.alibaba.fastjson.JSONObject;
import com.ymcoffee.entity.RecommendProductVo;
import com.ymcoffee.entity.RecommendTypeVo;
import com.ymcoffee.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequestMapping("/app/home")
@RestController
public class AppHomeController {

    @Autowired
    private RecommendService recommendService;

    @PostMapping("/recommend/banner")
    public List<String> banner() {
        return recommendService.getBannerList();
    }

    @PostMapping("/recommend/index")
    public JSONObject index() {
        JSONObject json = new JSONObject();
        List<String> bannerList = recommendService.getBannerList();
        List<RecommendTypeVo> recommendTypeList = recommendService.getRecommendTypeList();
        Map<Integer, List<RecommendProductVo>> recommendProductList = recommendService.getRecommendProductList();
        json.put("bannerList", bannerList);
        json.put("recommendTypeList", recommendTypeList);
        json.put("recommendProductList", recommendProductList);
        return json;
    }

}
