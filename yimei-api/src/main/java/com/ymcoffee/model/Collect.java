package com.ymcoffee.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Collect extends BaseModel {
	@Column(name = "user_id")
	private Long userId;
	@Column(name = "prod_id")
	private Long prodId;
}
