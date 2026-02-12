package com.example.thumbsup.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    // 密钥
    private static final String SECRET_KEY = "MySuperSecretKey_ThumbsUp_Project";
    // 过期时间，这里设为 24 小时
    private static final long EXPIRATION_TIME = 86400000L;

    /**
     * 生成 Token
     */
    public String generateToken(String userId) {
        return Jwts.builder()
                .setSubject(userId) // 把 userId 存进去
                .setIssuedAt(new Date()) // 签发时间
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // 过期时间
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // 签名算法和密钥
                .compact();
    }

    /**
     * 解析 Token 获取 UserId
     */
    public String parseToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject(); // 返回生成时放入的 userId
        } catch (Exception e) {
            // 解析失败会抛异常
            return null;
        }
    }
}