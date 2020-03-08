package net.cn.yasir.framework.restful.util;

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

    public static void main(String[] args) {
//        UserContext userContext = new UserContext();
//        userContext.setUserId(1);
//        userContext.setUserAccount("yasir");
//        userContext.setRoleId(1);
//        userContext.setRoleName("超级管理员");
//        System.out.println(encode(userContext, "VcZjX8dTYI4pAIxH", 1000 * 60L, 1000 * 10L));
//        String jwtToken = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlLW5hbWUiOiLotoXnuqfnrqHnkIblkZgiLCJyb2xlLWlkIjoxLCJ1c2VyLWFjY291bnQiOiJ5YXNpciIsInVzZXItaWQiOjEsImp0aSI6IjMyY2U0NzY0LTY0YmEtNDYwZC1iMWUzLTFlNTc2Y2M3NGFiZiIsImlhdCI6MTU4MzY2NjEyNCwic3ViIjoieWFzaXIiLCJleHAiOjE1ODM2NjY4NDR9.B7XHBhOYpbhrBpmAqGkdsly6I15wTSOlLZBiPueY99o";
//        System.out.println(JSONObject.toJSONString(decode(jwtToken, "yasir")));
    }

    /**
     * 编码
     * @param claimsMap
     * @param jwtSecret
     * @param jwtExpire
     * @return
     */
    public static String encode(Map<String, Object> claimsMap, String jwtSecret, long jwtExpire) {
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
     * 解码
     * @param jwtToken
     * @param jwtSecret
     * @return
     */
    public static Map<String, Object> decode(String jwtToken, String jwtSecret) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(jwtToken)
                .getBody();
        return claims;
    }

}
