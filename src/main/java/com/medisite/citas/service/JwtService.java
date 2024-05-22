package com.medisite.citas.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {
    @Value("${security.jwt.secret-key}")
    private String secretKey;

    @Value("${security.jwt.expiration-time}")
    private long jwtExpiration;


    public String extractUsername(String token){
        return extractClaims(token, Claims::getSubject);
    }

    public Date extractExpirationDate(String token){
        return extractClaims(token, Claims::getExpiration);
    }

    public <T> T extractClaims(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String token){
        return Jwts.parser().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
    }

    private boolean isTokenExpired(String token){
        return extractExpirationDate(token).before(new Date());
    }

    public boolean validateIdInToken(String token, long id){
        Claims claims = extractAllClaims(token);
        return (((Number) claims.get("id")).longValue() == (id));
    }

    public boolean isPaciente(String token){
        Claims claims = extractAllClaims(token);
        return (((String) claims.get("role")).equals("paciente"));
    }

    public boolean isAdmin(String token){
        Claims claims = extractAllClaims(token);
        return (((String) claims.get("role")).equals("admin"));
    }

    public boolean isMedico(String token) {
        Claims claims = extractAllClaims(token);
        return (((String) claims.get("role")).equals("medico"));
    }
}
