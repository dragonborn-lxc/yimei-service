package com.ymcoffee.mapper;

import com.ymcoffee.mapper.entity.YmRecommendType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface YmRecommendTypeMapper {

    @Select("select id,name from ym_recommend_type")
    List<YmRecommendType> queryAll();

}
