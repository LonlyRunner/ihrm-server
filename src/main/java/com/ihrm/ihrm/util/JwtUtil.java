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

    public String createToken(Long userId) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);
        
        return Jwts.builder()
                .setSubject(userId.toString())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SECRET_KEY)
                .compact();
    }

    // ==========================
    // 🔥 自动解构 Bearer，超强容错
    // ==========================
    public Long getUserIdFromToken(String authHeader) {
        try {
            System.out.println("========== Token调试 ==========");
            System.out.println("收到: " + authHeader);

            if (authHeader == null || authHeader.isBlank()) {
                throw new RuntimeException("token不能为空");
            }

            String token = authHeader;
            if (token.startsWith("Bearer ")) {
                token = token.substring(7).trim();
            }

            if (token.isEmpty()) {
                throw new RuntimeException("token不能为空");
            }

            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return Long.parseLong(claims.getSubject());

        } catch (io.jsonwebtoken.JwtException e) {
            System.err.println("JWT解析失败: " + e.getMessage());
            throw new RuntimeException("token无效或已过期，请重新登录");
        } catch (NumberFormatException e) {
            System.err.println("用户ID格式错误: " + e.getMessage());
            throw new RuntimeException("token数据异常，请重新登录");
        } catch (Exception e) {
            System.err.println("解析失败: " + e.getMessage());
            throw new RuntimeException("token无效：" + e.getMessage());
        }
    }
}