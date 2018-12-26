package com.ymcoffee.model;

import com.ymcoffee.enums.SexEnum;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Where(clause = "is_deleted=0")
@SQLDelete(sql = "update ym_user set is_deleted=1,update_time=now() where id=?")
public class User extends BaseModel{
    private String nickname;
    private String realname;
    private String mobile;
    private String portal;
    private LocalDateTime birthday;

    @Enumerated(EnumType.STRING)
    private SexEnum sexEnum;

    private String password;
}
