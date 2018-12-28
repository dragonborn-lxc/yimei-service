package com.ymcoffee.controller.app;

import com.ymcoffee.base.exception.ExceptionCode;
import com.ymcoffee.base.exception.ServiceException;
import com.ymcoffee.base.http.handler.OpenInterface;
import com.ymcoffee.base.tools.BeanUtils;
import com.ymcoffee.dao.hibernate.UserRepository;
import com.ymcoffee.entity.UserVo;
import com.ymcoffee.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/app/user")
@RestController
public class AppUserController {

    @Autowired
    private UserRepository userRepository;

    @OpenInterface
    @RequestMapping("/open/merge")
    public UserVo merge(@RequestBody UserVo userVo) {
        User user = userRepository.findOne(userVo.getId());
        if (user == null) {
            throw new ServiceException(ExceptionCode.INVALID_USER, "用户不存在");
        }
        BeanUtils.copyNotNullProperties(userVo, user);
        userRepository.merge(user);

        return UserVo.from(user);
    }
}
