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
@SQLDelete(sql = "update ym_recommend_product set is_deleted=1,update_time=now() where id=?")
public class RecommendProduct extends BaseModel {
    @Column(name = "recommend_type")
    private Integer recommendType;

    @Column(name = "prod_type")
    private Integer prodType;

    @Column(name = "prod_id")
    private Long prodId;

    @Column(name = "prod_name")
    private String prodName;

    @Column(name = "img_url")
    private String imgUrl;
}
