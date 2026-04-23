package com.ihrm.ihrm.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    private static final String KEY = "12345678901234567890123456789012";
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(KEY.getBytes());

    private static final long EXPIRATION_TIME = 100L * 365 * 24 * 60 * 60 * 1000;

    // 生成 token 传入 String
    public String createToken(String userId) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SECRET_KEY)
                .compact();
    }

    // 解析 token 直接返回 String，不再转 Long！！！
    public String getUserIdFromToken(String authHeader) {
        try {
            if (authHeader == null || authHeader.isBlank()) {
                throw new RuntimeException("token不能为空");
            }

            String token = authHeader;
            if (token.startsWith("Bearer ")) {
                token = token.substring(7).trim();
            }

            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            // ✅ 直接返回 String，不转 Long
            return claims.getSubject();

        } catch (Exception e) {
            throw new RuntimeException("token无效：" + e.getMessage());
        }
    }
}