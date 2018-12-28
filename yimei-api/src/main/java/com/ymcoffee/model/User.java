package com.ymcoffee.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ymcoffee.base.tools.LocalDateTimeDeserializer;
import com.ymcoffee.base.tools.LocalDateTimeSerializer;
import com.ymcoffee.enums.SexEnum;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
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

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @Column(columnDefinition = "date")
    private LocalDate birthday;

    @Enumerated(EnumType.STRING)
    private SexEnum sexEnum;

    private String password;
}
