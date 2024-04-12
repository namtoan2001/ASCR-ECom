package org.example.acsrecomapi.Services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.example.acsrecomapi.DTO.JWT.GenerateJwtRequest;
import org.example.acsrecomapi.Interfaces.IJwtService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
@Service
public class JwtService implements IJwtService {
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.issuer}")
    private String jwtIssuer;
    private Key jwtSecretKey;
    @PostConstruct
    public void init() {
        jwtSecretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }
    @Override
    public String generateJwt(GenerateJwtRequest request) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + 86400000);
        return Jwts.builder()
                .setSubject(String.valueOf(request.getUserId()))
                .claim("userName", request.getUserName())
                .claim("fullName", request.getFullName())
                .claim("email", request.getEmail())
                .claim("numberPhone", request.getNumberPhone())
                .claim("isActive", request.isActive())
                .setIssuedAt(now)
                .setIssuer(jwtIssuer)
                .setExpiration(expiration)
                .signWith(jwtSecretKey)
                .compact();
    }

    @Override
    public Claims decodeJwt(String token) throws JwtException {
        try {
            Jws<Claims> jws = Jwts.parser().setSigningKey(jwtSecretKey).build().parseClaimsJws(token);
            return jws.getBody();
        } catch (JwtException e) {
            System.err.println("JWT Decoding Error: " + e.getMessage());
            throw e;
        }
    }
}
