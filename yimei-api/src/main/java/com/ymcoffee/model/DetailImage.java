package com.ymcoffee.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@Where(clause = "is_deleted=0")
@SQLDelete(sql = "update ym_detail_imgage set is_deleted=1,update_time=now() where id=?")
public class DetailImage extends BaseModel {
	@Column(name = "prod_id")
	private Long prodId;
	@Column(name = "img_type")
	private Integer imgType;
	@Column(name = "img_url")
	private String imgUrl;
}
