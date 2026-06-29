package com.bank.customer_service.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    private Key getKey() {

        return Keys.hmacShaKeyFor(
                secret.getBytes());

    }

    public String generateToken(String email, String role) {

        return Jwts.builder()

                .setSubject(email)

                .claim("role", role)

                .setIssuedAt(new Date())

                .setExpiration(
                        new Date(
                                System.currentTimeMillis() + 86400000))

                .signWith(getKey(), SignatureAlgorithm.HS256)

                .compact();

    }

    public String extractEmail(String token) {

        return Jwts.parserBuilder()

                .setSigningKey(getKey())

                .build()

                .parseClaimsJws(token)

                .getBody()

                .getSubject();

    }

    public String extractRole(String token) {

        return Jwts.parserBuilder()

                .setSigningKey(getKey())

                .build()

                .parseClaimsJws(token)

                .getBody()

                .get("role", String.class);

    }

}