package com.example.Real.Estate.Management.System.utils;
import com.example.Real.Estate.Management.System.enums.Role;
import com.example.Real.Estate.Management.System.models.User;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.security.Keys;


import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Component
public class JwtUtil {

    @Value("${app.jwt.secret-key}")
    private String jwtSecretKey;

    @Value("${app.jwt.expiration-ms}")
    private long jwtExpirationMs;

    public String generateToken(User user) {

        Date expiration = new Date(System.currentTimeMillis() + (10 * 365 * 24 * 60 * 60 * 1000));
        SecretKey key = Keys.hmacShaKeyFor(jwtSecretKey.getBytes());

        Map<String, String> claims = new HashMap<>();
        claims.put("userId",String.valueOf(user.getId()));
        claims.put("role", user.getRole());

        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuer("self")
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(expiration)
                .signWith(key)
                .compact();
    }

    public boolean isAdmin(String token){
        String tokenRole = getRoleFromToken(token); // Extract role from the token
        return tokenRole.equals(Role.ADMIN.name());
    }

    public boolean isAgent(String token){
        String tokenRole = getRoleFromToken(token); // Extract role from the token
        return tokenRole.equals(Role.AGENT.name());
    }

    private Claims extractClaims(String token) {
        SecretKey key = Keys.hmacShaKeyFor(jwtSecretKey.getBytes());

        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Long getUserIdFromToken(String token) {
        Claims claims = extractClaims(token);
        return Long.parseLong(claims.get("userId").toString());
    }

    public String getRoleFromToken(String token) {
        Claims claims = extractClaims(token);
        return claims.get("role").toString();
    }

    public boolean isGuest(String token){
            String tokenRole = getRoleFromToken(token); // Extract role from the token
            return tokenRole.equals(Role.GUEST.name()); // Compare with required role
    }
    public boolean isTokenValid(String token) {
        try {
            Claims claims = extractClaims(token);
            Date expiration = claims.getExpiration();
            return expiration.after(new Date());
        } catch (ExpiredJwtException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
    }

}