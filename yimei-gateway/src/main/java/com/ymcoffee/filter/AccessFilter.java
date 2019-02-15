package com.ymcoffee.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.ymcoffee.util.JwtUtils;
import com.ymcoffee.util.RedisUtils;
import io.jsonwebtoken.JwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;

@Component
public class AccessFilter extends ZuulFilter {

    private static final Logger logger = LoggerFactory.getLogger(AccessFilter.class);

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 2;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        //登入不进行token拦截
        if (request.getRequestURI().contains("/login")) {
            return false;
        }
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        String realIp = request.getHeader("X-Real-IP");
        String grantType = request.getHeader("grant_type");
        Jedis jedis = null;
        try {
            if (grantType != null && grantType.equals("refresh")) {
                String oldRefreshToken = request.getHeader("refresh_token");
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
                String refreshToken = jedis.get(account);
                if (!oldRefreshToken.equals(refreshToken)) {
                    logger.info("IP: [" + realIp + "] refresh_token is wrong,need to relogin");
                    context.setSendZuulResponse(false);
                    context.setResponseStatusCode(401);
                    context.setResponseBody("access denied");
                    context.getResponse().setContentType("application/json;charset=UTF-8");
                }
            } else {
                String accessToken = request.getHeader("access_token");
                if (accessToken == null || "".equals(accessToken)) {
                    logger.info("IP: [" + realIp + "] access_token is empty,request denied");
                    context.setSendZuulResponse(false);
                    context.setResponseStatusCode(403);
                    context.setResponseBody("request denied");
                    context.getResponse().setContentType("text/html;charset=UTF-8");
                    return null;
                }
                jedis = RedisUtils.getJedisPoolInstance().getResource();
                boolean existed = jedis.exists(accessToken);
                if (!existed) {
                    logger.info("IP: [" + realIp + "] access_token is wrong,need refresh_token to get latest token");
                    context.setSendZuulResponse(false);
                    context.setResponseStatusCode(601);
                    context.setResponseBody("access denied");
                    context.getResponse().setContentType("application/json;charset=UTF-8");
                }
            }
        } catch (JwtException e) {
            logger.info("IP: [" + realIp + "] refresh_token expired or be wrong,need to relogin");
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(401);
            context.setResponseBody("access denied");
            context.getResponse().setContentType("application/json;charset=UTF-8");
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
        logger.info("IP: [" + realIp + "] request");
        return null;
    }
}
