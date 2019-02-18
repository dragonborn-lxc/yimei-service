package com.ymcoffee.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import redis.clients.jedis.Jedis;

public class TokenUtils {

    public static JSONObject generate(String account) throws Exception {
        if (account == null || "".equals(account)) {
            throw new Exception("account can not be null");
        }
        JSONObject json = new JSONObject();
        JSONObject tokenInfo = new JSONObject();
        Jedis jedis = null;
        String now = String.valueOf(System.currentTimeMillis());
        try {
            //2 hours access token
            String accessToken = DigestUtils.sha1Hex(now);
            //30 days refresh token
            String refreshToken = JwtUtils.createJWT(now, "yimei", account, 604800000L);
            tokenInfo.put("access_token", accessToken);
            tokenInfo.put("refresh_token", refreshToken);

            //save
            jedis = RedisUtils.getJedisPoolInstance().getResource();
            jedis.set(accessToken, "1", "NX", "EX", 7200);
            jedis.set(account, tokenInfo.toJSONString(), "NX", "EX", 2592000);

            json.put("access_token", accessToken);
            json.put("refresh_token", refreshToken);
            return json;
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    public static JSONObject refresh(String oldRefreshToken) throws Exception {
        if (oldRefreshToken == null || "".equals(oldRefreshToken)) {
            throw new Exception("refreshToken can not be null");
        }
        Jedis jedis = null;
        try {
            String account = JwtUtils.parseJWT(oldRefreshToken).getSubject();
            jedis = RedisUtils.getJedisPoolInstance().getResource();
            String text = jedis.get(account);
            if (text == null || "".equals(text)) {
                throw new Exception("refreshToken does not exist,need to be relogin");
            }
            JSONObject tokenInfo = JSON.parseObject(text);
            if (!oldRefreshToken.equals(tokenInfo.getString("refresh_token"))) {
                throw new Exception("refreshToken is not correct,need to be relogin");
            }

            //del old refresh token
            jedis.del(account);
            jedis.del(tokenInfo.getString("access_token"));

            //generate new token
            JSONObject json = TokenUtils.generate(account);
            return json;
        } catch(Exception e) {
            throw new Exception("refreshToken is not correct,need to be relogin");
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    public static void revoke(String account) throws Exception {
        if (account == null || "".equals(account)) {
            throw new Exception("account can not be null");
        }
        Jedis jedis = null;
        try {
            jedis = RedisUtils.getJedisPoolInstance().getResource();
            String text = jedis.get(account);
            if (text != null && !"".equals(text)) {
                JSONObject tokenInfo = JSON.parseObject(text);
                jedis.del(tokenInfo.getString("access_token"));
                jedis.del(account);
            }
        } catch(Exception e) {
            throw new Exception("account or accessToken is not correct");
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

}
