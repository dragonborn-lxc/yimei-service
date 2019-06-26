package com.ymcoffee.config;

import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.RateLimitKeyGenerator;
import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.RateLimitUtils;
import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.properties.RateLimitProperties;
import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.support.DefaultRateLimitKeyGenerator;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;

/**
 * File Name: ZuulRateLimitConfig
 * General Description: Copyright and file header.
 * Revision History:
 * Modification
 * Author                Date(MM/DD/YYYY)   JiraID           Description of Changes
 * ---------------------   ------------    ----------     -----------------------------
 * Le xing chen            2019/4/16
 */
@Configuration
public class ZuulRateLimitConfig {

	/**
	 * Key Generator
	 *
	 * @param properties
	 * @param rateLimitUtils
	 * @return
	 */
	@Bean
	public RateLimitKeyGenerator ratelimitKeyGenerator(final RateLimitProperties properties, final RateLimitUtils rateLimitUtils) {
		return new DefaultRateLimitKeyGenerator(properties, rateLimitUtils) {
			@Override
			public String key(HttpServletRequest request, Route route, RateLimitProperties.Policy policy) {
				String ip = request.getHeader("X-REAL-IP");
				if (ip == null || ip.trim() == "" || "unknown".equalsIgnoreCase(ip)) {
					ip = request.getRemoteAddr();
				}
				return super.key(request, route, policy) + "_" + ip + "_" + request.getRequestURI();
			}
		};
	}

}
