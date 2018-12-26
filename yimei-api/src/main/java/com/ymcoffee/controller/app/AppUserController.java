package com.ymcoffee.controller.app;

import com.ymcoffee.model.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/app/user")
@RestController
public class AppUserController {


    public User register() {
        return null;
    }

    public User login() {
        return null;
    }

    public User resetPassword() {
        return null;
    }

    public void sendCode() {

    }

    public Boolean verifyCode() {
        return null;
    }
}
