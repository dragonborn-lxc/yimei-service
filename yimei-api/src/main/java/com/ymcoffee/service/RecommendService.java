package com.ymcoffee.service;

import com.ymcoffee.dao.hibernate.BannerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendService {

    @Autowired
    private BannerRepository bannerRepository;

    public List<String> getBannerList() {
        return bannerRepository.findAll();
    }

}
