package com.ymcoffee.entity;

import com.ymcoffee.entity.base.PageVo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CollectParamsVo extends PageVo {
	private Long userId;
	private Long prodId;
}
