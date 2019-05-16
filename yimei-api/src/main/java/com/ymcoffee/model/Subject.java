package com.ymcoffee.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Subject extends BaseModel {
	private String url;
	@Column(name = "img_url")
	private String imgUrl;
	private Integer seq;
}
