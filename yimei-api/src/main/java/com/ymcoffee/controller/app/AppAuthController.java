package com.ymcoffee.controller.app;

import com.alibaba.fastjson.JSONObject;
import com.ymcoffee.base.exception.ExceptionCode;
import com.ymcoffee.base.exception.ServiceException;
import com.ymcoffee.base.http.handler.OpenInterface;
import com.ymcoffee.base.tools.MD5;
import com.ymcoffee.dao.hibernate.UserRepository;
import com.ymcoffee.entity.UserVo;
import com.ymcoffee.model.User;
import com.ymcoffee.service.SmsVerifyService;
import com.ymcoffee.util.TokenUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@OpenInterface
@RequestMapping("/app/auth")
@RestController
public class AppAuthController {

    @Autowired
    private SmsVerifyService smsVerifyService;

    @Autowired
    private UserRepository userRepository;


    /**
     * 发送短信验证码
     */
    @PostMapping("/open/sendSms")
    public String sendSms(String mobile) {
        if(StringUtils.isEmpty(mobile)) {
            throw new ServiceException(ExceptionCode.PARAM_TYPE_ERROR, "请输入手机号码");
        }
        // todo 为了测试方便返回code，后续不能返回
        String code = smsVerifyService.sendSmsVerify(mobile);
        System.out.println(code);
        return code;
    }

    /**
     * 注册
     */
    @PostMapping("/open/register")
    public UserVo register(String mobile, String code, String password) {
        if(StringUtils.isEmpty(mobile)
                || StringUtils.isEmpty(code)
                || StringUtils.isEmpty(password)) {
            throw new ServiceException(ExceptionCode.PARAM_TYPE_ERROR, "参数不能为空");
        }

        if(CollectionUtils.isNotEmpty(userRepository.findAllByMobile(mobile))) {
            throw new ServiceException(ExceptionCode.PARAM_TYPE_ERROR, "该用户已经存在");
        }

        smsVerifyService.validSmsVerify(mobile, code);

        User user = new User();
        user.setMobile(mobile);
        user.setNickname(mobile);
        user.setRealname(mobile);
        user.setPassword(MD5.MD5Encode(password));
        userRepository.save(user);
        return UserVo.from(user);
    }

    /**
     * 登录
     */
    @PostMapping("/open/login")
    public UserVo login(String mobile, String password) {
        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
            throw new ServiceException(ExceptionCode.PARAM_TYPE_ERROR, "手机号和密码不能为空");
        }

        List<User> allByMobileAndPassword = userRepository.findAllByMobileAndPassword(mobile, MD5.MD5Encode(password));
        if(CollectionUtils.isEmpty(allByMobileAndPassword)) {
            throw new ServiceException(ExceptionCode.INVALID_USER, "账号或密码错误");
        }

        //获取accessToken和refreshToken
        JSONObject token = null;
        try {
            TokenUtils.revoke(mobile);
            token = TokenUtils.generate(mobile);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.SYSTEM, "生产token失败");
        }

        return UserVo.from(allByMobileAndPassword.get(0), token.getString("access_token"), token.getString("refresh_token"));
    }

    /**
     * 验证短信验证码
     */
    @PostMapping("/open/verifySms")
    public Boolean verifySms(String mobile, String code) {
        smsVerifyService.validSmsVerify(mobile, code);
        return true;
    }

    /**
     * 修改密码
     */
    @PostMapping("/open/resetPassword")
    public UserVo resetPassword(String mobile, String password) {
        List<User> users = userRepository.findAllByMobile(mobile);
        if(CollectionUtils.isEmpty(users)) {
            throw new ServiceException(ExceptionCode.INVALID_USER, "该手机号码不存在系统");
        }
        User user = users.get(0);
        user.setPassword(MD5.MD5Encode(password));
        userRepository.merge(user);
        return UserVo.from(user);
    }

    /**
     * 获取刷新token
     * @param refreshToken
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/token/refresh", produces = "application/json")
    public JSONObject refresh(@RequestParam("refresh_token") String refreshToken) {
        JSONObject obj = null;
        try {
            obj = TokenUtils.refresh(refreshToken);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}
