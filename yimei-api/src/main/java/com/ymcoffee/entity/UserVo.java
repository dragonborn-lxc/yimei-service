package com.ymcoffee.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ymcoffee.base.tools.BeanUtils;
import com.ymcoffee.base.tools.LocalDateDeserializer;
import com.ymcoffee.base.tools.LocalDateSerializer;
import com.ymcoffee.enums.SexEnum;
import com.ymcoffee.model.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserVo {
    private Long id;
    private String nickname;
    private String realname;
    private String mobile;
    private String portal;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate birthday;
    private SexEnum sexEnum;

    public static UserVo from(User user) {
        UserVo vo = new UserVo();
        BeanUtils.copyNotNullProperties(user, vo);

        return vo;
    }
}
