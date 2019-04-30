package com.ymcoffee.service;

import com.ymcoffee.dao.hibernate.DictionaryRepository;
import com.ymcoffee.dao.hibernate.RecommendProductRepository;
import com.ymcoffee.entity.RecommendProductVo;
import com.ymcoffee.entity.RecommendTypeVo;
import com.ymcoffee.model.Dictionary;
import com.ymcoffee.model.RecommendProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RecommendService {

	@Autowired
	private DictionaryRepository dictionaryRepository;

	@Autowired
	private RecommendProductRepository recommendProductRepository;

	public List<RecommendTypeVo> getRecommendTypeList() {
		//查询所有首页推荐类别
		List<Dictionary> dictionaryList = dictionaryRepository.findAllByCodeOrderBySubcode(1);
		List<RecommendTypeVo> recommendTypeList = dictionaryList.stream().map(result -> {
			RecommendTypeVo obj = new RecommendTypeVo();
			obj.setId(result.getSubcode());
			obj.setName(result.getSubcodeDesc());
			return obj;
		}).collect(Collectors.toList());
		return recommendTypeList;
	}

	public Map<Integer, List<RecommendProductVo>> getRecommendProductList() {
		//查询所有首页推荐产品
		List<RecommendProduct> list = recommendProductRepository.findAll();
		Map<Integer, List<RecommendProductVo>> map = new HashMap<>();
		for (RecommendProduct obj : list) {
			RecommendProductVo recommendProductVo = new RecommendProductVo();
			recommendProductVo.setId(obj.getProdId());
			recommendProductVo.setName(obj.getProdName());
			recommendProductVo.setUrl(obj.getImgUrl());
			if (map.containsKey(obj.getRecommendType())) {
				map.get(obj.getRecommendType()).add(recommendProductVo);
			} else {
				List<RecommendProductVo> tmpList = new ArrayList<>();
				tmpList.add(recommendProductVo);
				map.put(obj.getRecommendType(), tmpList);
			}
		}
		return map;
	}

}
