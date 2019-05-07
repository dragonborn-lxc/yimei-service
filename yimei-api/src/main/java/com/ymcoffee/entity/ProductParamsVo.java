package com.ymcoffee.entity;

import com.ymcoffee.entity.base.PageVo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductParamsVo extends PageVo {
	private Integer category;
	private Integer theme;
	private Integer texture;
}
