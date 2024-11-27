package com.office.booklink.util;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

public class JwtUtils {
    private final String SECRET_KEY = "9A9DE323B633223C8E69121324DDED522BEF17C42FEF4644971549A8AABOOKLINK";
                                       

    private SecretKey getSigningKey() {
//        byte[] keyBytes = Decoders.BASE64.decode(this.SECRET_KEY);
        byte[] keyBytes = this.SECRET_KEY.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // JWT 생성
//    public String generateToken(Authentication authentication) {
//        // 인증 정보에서 사용자 이름 추출
//        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
//
//        return Jwts.builder()
//                .subject(username)
//                .issuedAt(new Date())
//                .expiration(new Date((new Date()).getTime() + 3600000))
//                .signWith(this.getSigningKey())
//                .compact();
//    }

    public Claims extractAllClaims(String token){
        return Jwts.parser()
                .verifyWith(this.getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
   
}