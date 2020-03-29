package net.cn.yasir.framework.tool.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * @desc: JWT生成器
 * @author: yasir
 * @date: 2020/3/8 17:31
 */
public class JwtUtil {

    /**
     * 加密
     * @param claimsMap
     * @param jwtSecret
     * @param jwtExpire
     * @return
     */
    public static String encrypt(Map<String, Object> claimsMap, String jwtSecret, long jwtExpire) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        JwtBuilder jwtBuilder = Jwts.builder()
                .addClaims(claimsMap)
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(now)
                .setSubject("JWT_SUBJECT")
                .signWith(SignatureAlgorithm.HS256, jwtSecret);
        if(jwtExpire > 0) {
            long expMillis = nowMillis + jwtExpire;
            Date exp = new Date(expMillis);
            jwtBuilder.setExpiration(exp);
        }
        return jwtBuilder.compact();
    }

    /**
     * 解密
     * @param jwtToken
     * @param jwtSecret
     * @return
     */
    public static Map<String, Object> decrypt(String jwtToken, String jwtSecret) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(jwtToken)
                .getBody();
        return claims;
    }

}
