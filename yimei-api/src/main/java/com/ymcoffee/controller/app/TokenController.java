package com.ymcoffee.controller.app;

import com.alibaba.fastjson.JSONObject;
import com.ymcoffee.util.TokenUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
public class TokenController {

    @RequestMapping(method = RequestMethod.POST, value = "/refresh", consumes = "application/json", produces = "application/json")
    public JSONObject refresh(@RequestBody String refreshToken) {
        JSONObject obj = null;
        try {
            obj = TokenUtils.refresh(refreshToken);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

}
