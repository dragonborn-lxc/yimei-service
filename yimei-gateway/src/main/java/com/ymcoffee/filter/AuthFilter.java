package com.ymcoffee.filter;

import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.ymcoffee.util.JwtUtils;
import com.ymcoffee.util.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;

@Component
public class AuthFilter extends ZuulFilter {

    private static final Logger logger = LoggerFactory.getLogger(AuthFilter.class);

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        //token refresh需进行拦截
        if (request.getRequestURI().contains("/token/refresh")) {
            return true;
        }
        return false;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        String realIp = request.getHeader("X-Real-IP");
        String oldRefreshToken = request.getParameter("refresh_token");
        Jedis jedis = null;
        if (oldRefreshToken == null || "".equals(oldRefreshToken)) {
            logger.info("IP: [" + realIp + "] refresh_token is empty,request denied");
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(403);
            context.setResponseBody("request denied");
            context.getResponse().setContentType("text/html;charset=UTF-8");
            return null;
        }
        String account = JwtUtils.parseJWT(oldRefreshToken).getSubject();
        jedis = RedisUtils.getJedisPoolInstance().getResource();
        String tokenInfo = jedis.get(account);
        if (tokenInfo == null || "".equals(tokenInfo)) {
            logger.info("IP: [" + realIp + "] refresh_token does not existed,need to relogin");
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(401);
            context.setResponseBody("access denied");
            context.getResponse().setContentType("text/html;charset=UTF-8");
            return null;
        }
        String refreshToken = JSONObject.parseObject(tokenInfo).getString("refresh_token");
        if (!oldRefreshToken.equals(refreshToken)) {
            logger.info("IP: [" + realIp + "] refresh_token is wrong,need to relogin");
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(401);
            context.setResponseBody("access denied");
            context.getResponse().setContentType("text/html;charset=UTF-8");
        }
        return null;
    }
}
