package com.ymcoffee.service;

import com.ymcoffee.dao.hibernate.BannerRepository;
import com.ymcoffee.model.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecommendService {

    @Autowired
    private BannerRepository bannerRepository;

    public List<String> getBannerList() {
        List<Banner> list = bannerRepository.findAll();
        return list.stream().map(Banner::getUrl).collect(Collectors.toList());
    }

}
