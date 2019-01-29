package com.ymcoffee.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class RequestFilter extends ZuulFilter {

    private static final Logger logger = LoggerFactory.getLogger(RequestFilter.class);

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
        //登入不进行token拦截
        if ("/test/test".equals(request.getRequestURI())) {
            return false;
        }
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        String grantType = request.getHeader("grant_type");
        if (grantType != null && grantType.equals("refresh")) {
            String refreshToken = request.getHeader("refresh_token");
            if (refreshToken == null || "".equals(refreshToken)) {
                logger.info("IP: [" + request.getRemoteAddr() + "] refresh_token is empty,request denied");
                context.setSendZuulResponse(false);
                context.setResponseStatusCode(403);
                context.setResponseBody("request denied");
                context.getResponse().setContentType("text/html;charset=UTF-8");
            }
        } else {
            String accessToken = request.getHeader("access_token");
            if (accessToken == null || "".equals(accessToken)) {
                logger.info("IP: [" + request.getRemoteAddr() + "] access_token is empty,request denied");
                context.setSendZuulResponse(false);
                context.setResponseStatusCode(403);
                context.setResponseBody("request denied");
                context.getResponse().setContentType("text/html;charset=UTF-8");
            }
        }
        return null;
    }
}
