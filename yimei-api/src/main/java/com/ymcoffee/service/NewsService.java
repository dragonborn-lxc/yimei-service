package com.ymcoffee.service;

import com.ymcoffee.dao.hibernate.NewsRepository;
import com.ymcoffee.entity.NewsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsService {

	@Autowired
	private NewsRepository newsRepository;

	public List<NewsVo> getNewsList() {
		//查询所有首页推荐类别
		List<NewsVo> newsList = newsRepository.findAll().stream().map(result -> {
			NewsVo obj = new NewsVo();
			obj.setUrl(result.getUrl());
			obj.setImgUrl(result.getImgUrl());
			return obj;
		}).collect(Collectors.toList());
		return newsList;
	}

}
