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
@SQLDelete(sql = "update ym_art set is_deleted=1,update_time=now() where id=?")
public class Art extends BaseModel {
    private String name;
    private Integer price;
    private String author;
    private Integer category;
    private Integer theme;
    private String size;

    @Column(name = "size_classify")
    private Integer sizeClassify;
    private Integer year;
    private String description;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;
}
