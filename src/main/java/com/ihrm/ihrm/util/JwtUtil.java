package com.ihrm.ihrm.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class JwtUtil {

    private static final String KEY = "12345678901234567890123456789012";
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(KEY.getBytes());

    public String createToken(Long userId) {
        return Jwts.builder()
                .setSubject(userId.toString())
                .signWith(SECRET_KEY)
                .compact();
    }

    public Long getUserIdFromToken(String token) {
        try {
            System.out.println("========== Token调试信息 ==========");
            System.out.println("原始token: [" + token + "]");
            System.out.println("原始token长度: " + (token == null ? 0 : token.length()));

            if (token == null || token.trim().isEmpty()) {
                throw new IllegalArgumentException("token不能为空，请先登录获取token");
            }

            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }

            System.out.println("处理后的token: [" + token + "]");
            System.out.println("处理后token长度: " + token.length());
            System.out.println("========== 调试结束 ==========");

            if (token.isEmpty()) {
                throw new IllegalArgumentException("无效的token格式，请先登录获取有效的token");
            }

            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return Long.parseLong(claims.getSubject());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e.getMessage());
        } catch (Exception e) {
            System.err.println("Token解析异常: " + e.getClass().getName() + " - " + e.getMessage());
            throw new RuntimeException("token解析失败，请确认token是否有效");
        }
    }
}
