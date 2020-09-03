package com.xiong.user.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;

@Component
public class JwtUtil {

    @Autowired
    private RsaKeyProperties rsaKeyProperties;
    private static final String CLAIMS_KEY_USERNAME = "username";

    // 生成token返回
    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIMS_KEY_USERNAME, userDetails.getUsername());
        return createToken(claims, userDetails.getUsername());
    }

    // 创建token
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setId(createJTI()).setClaims(claims).setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JwtConstant.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.RS256, rsaKeyProperties.getPrivateKey()).compact();
    }

    // 创建JTI
    private String createJTI() {
        return new String(Base64.getEncoder().encode(UUID.randomUUID().toString().getBytes()));
    }

    // 校验token
    public Boolean validateToken(String token, UserDetails userDetails){
        String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    // 校验token是否过期
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(rsaKeyProperties.getPublicKey())
                .parseClaimsJws(token).getBody();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // 刷新token
    public String refreshToken(){
        return null;
    }
}
