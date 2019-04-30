package com.ymcoffee.controller.app;

import com.alibaba.fastjson.JSONObject;
import com.ymcoffee.entity.NewsVo;
import com.ymcoffee.entity.RecommendProductVo;
import com.ymcoffee.entity.RecommendTypeVo;
import com.ymcoffee.service.NewsService;
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

	@Autowired
	private NewsService newsService;

	@PostMapping("/recommend/index")
	public JSONObject recommendIndex() {
		JSONObject json = new JSONObject();
		List<RecommendTypeVo> recommendTypeList = recommendService.getRecommendTypeList();
		Map<Integer, List<RecommendProductVo>> recommendProductList = recommendService.getRecommendProductList();
		json.put("recommendTypeList", recommendTypeList);
		json.put("recommendProductList", recommendProductList);
		return json;
	}

	@PostMapping("/news/index")
	public List<NewsVo> newsIndex() {
		return newsService.getNewsList();
	}

}
