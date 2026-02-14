package com.ActiFitFlowApp.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.List;

@Component
public class JwtUtils {

    private final String jwtSecert =
            "mySuperSecretKeyForJwtMySuperSecretKey123";

    private final int jwtExpirationMs = 172800000;

    public String getJwtFromHeader(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.toLowerCase().startsWith("bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }


    public String generateToken(String userId , String role){
        return Jwts.builder()
                .subject(userId)
                .claim("roles", List.of(role))
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(key())
                .compact();
    }

    public Boolean ValidateJwtToken(String jwtToken){
        try {
            Jwts.parser().verifyWith((SecretKey) key()).build().parseSignedClaims(jwtToken);
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    private Key key() {
        return Keys.hmacShaKeyFor(
                jwtSecert.getBytes(StandardCharsets.UTF_8)
        );
    }


    public String getUserIdFromToken(String jwt) {
        return Jwts.parser().verifyWith((SecretKey) key()).build().parseSignedClaims(jwt).getPayload().getSubject();
    }

    public Claims getAllClaims(String jwt) {
        return Jwts.parser().verifyWith((SecretKey) key()).build().parseSignedClaims(jwt).getPayload();
    }
}