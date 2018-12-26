package com.ymcoffee.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;


@Entity
@Getter
@Setter
@Where(clause = "is_deleted=0")
@SQLDelete(sql = "update ym_Sms_Verify set is_deleted=1,update_time=now() where id=?")
public class SmsVerify extends BaseModel {
    private String mobile;
    private String code;
    private Boolean valid;
}
