package com.ymcoffee.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;

@Getter
@Setter
public class ProductClassifyVo {
	private BigInteger id;
	private String name;
	private String brand;
	private String year;
	private BigDecimal price;
	private String artist;
	private String category;
	private String theme;
	private String texture;
	private String size;
}
