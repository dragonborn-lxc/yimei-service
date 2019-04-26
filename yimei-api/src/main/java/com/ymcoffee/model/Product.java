package com.ymcoffee.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Where(clause = "is_deleted=0")
@SQLDelete(sql = "update ym_product set is_deleted=1,update_time=now() where id=?")
public class Product extends BaseModel {
	private Integer type;
	private String pid;
	private String name;
	private String brand;
	private String year;
	private BigDecimal price;
	private String artist;
	private Integer category;
	private Integer theme;
	private Integer texture;
	private String size;
	@Column(name = "size_classify")
	private Integer sizeClassify;
	private Integer mount;
	@Column(name = "delivery_address")
	private String deliveryAddress;
	@Column(name = "delivery_deadline")
	private String deliveryDeadline;
	private String scene;
	private String description;
	@Column(name = "classify_img_url")
	private String classifyImgUrl;
	@Column(name = "thumbnail_img_url")
	private String thumbnailImgUrl;
	@Column(name = "artist_signature")
	private Integer artistSignature;
	@Column(name = "refinement_category")
	private Integer refinementCategory;
	private Integer credentials;
	private Integer copyright;
}
