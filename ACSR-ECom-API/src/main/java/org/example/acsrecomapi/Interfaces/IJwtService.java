package org.example.acsrecomapi.Interfaces;

import org.example.acsrecomapi.DTO.JWT.GenerateJwtRequest;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;

public interface IJwtService {
    String generateJwt(GenerateJwtRequest request);

    Claims decodeJwt(String token) throws JwtException;
}
