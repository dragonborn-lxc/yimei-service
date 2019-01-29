package com.ymcoffee.util;

import io.jsonwebtoken.*;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.util.Date;

public class JwtUtils {

    public static String createJWT(String id, String issuer, String subject, long ttlMillis) throws Exception {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        SecretKey key = generalKey();
        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(issuer)
                .signWith(key, SignatureAlgorithm.HS256);
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        return builder.compact();
    }

    /**
     * 解密jwt
     *
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String jwt) throws JwtException {
        SecretKey key = generalKey();
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(jwt)
                .getBody();
        return claims;
    }

    /**
     * 由字符串生成加密key
     *
     * @return
     */
    private static SecretKey generalKey() {
        byte[] encodedKey = DatatypeConverter.parseBase64Binary("wxPsQjwfRDc7Tvrhf8VlQeM1wJDbRC7i7V1e5niJhOoP");
        SecretKey key = new SecretKeySpec(encodedKey, SignatureAlgorithm.HS256.getJcaName());
        return key;
    }

}
