package com.example.Real.Estate.Management.System.utils;
import com.example.Real.Estate.Management.System.enums.Role;
import com.example.Real.Estate.Management.System.models.User;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.security.Keys;


import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${app.jwt.secret-key}")
    private String jwtSecretKey;

    @Value("${app.jwt.expiration-ms}")
    private long jwtExpirationMs;

    public String generateToken(User user) {

        Date expiration = new Date(System.currentTimeMillis() + (10 * 365 * 24 * 60 * 60 * 1000));
        SecretKey key = Keys.hmacShaKeyFor(jwtSecretKey.getBytes());


        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuer(user.getRole())
                .setIssuedAt(new Date())
                .setExpiration(expiration)
                .signWith(key)
                .compact();


    }

    public boolean isAdmin(String token){
        SecretKey key = Keys.hmacShaKeyFor(jwtSecretKey.getBytes());
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String tokenRole = claims.getIssuer(); // Extract role from the token

            return tokenRole.equals(Role.ADMIN.name()); // Compare with required role

        } catch (ExpiredJwtException e) {
            System.out.println("Token expired!");
        } catch (MalformedJwtException e) {
            System.out.println("Invalid token format!");
        } catch (SignatureException e) {
            System.out.println("Invalid token signature!");
        } catch (Exception e) {
            System.out.println("Token validation failed!");
        }
        return false; // Return false if validation fails
    }

    public boolean isAgent(String token){
        SecretKey key = Keys.hmacShaKeyFor(jwtSecretKey.getBytes());
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String tokenRole = claims.getIssuer(); // Extract role from the token

            return tokenRole.equals(Role.AGENT.name()); // Compare with required role

        } catch (ExpiredJwtException e) {
            System.out.println("Token expired!");
        } catch (MalformedJwtException e) {
            System.out.println("Invalid token format!");
        } catch (SignatureException e) {
            System.out.println("Invalid token signature!");
        } catch (Exception e) {
            System.out.println("Token validation failed!");
        }
        return false; // Return false if validation fails
    }

    public boolean isGuest(String token){
        SecretKey key = Keys.hmacShaKeyFor(jwtSecretKey.getBytes());
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String tokenRole = claims.getIssuer(); // Extract role from the token

            return tokenRole.equals(Role.GUEST.name()); // Compare with required role

        } catch (ExpiredJwtException e) {
            System.out.println("Token expired!");
        } catch (MalformedJwtException e) {
            System.out.println("Invalid token format!");
        } catch (SignatureException e) {
            System.out.println("Invalid token signature!");
        } catch (Exception e) {
            System.out.println("Token validation failed!");
        }
        return false; // Return false if validation fails
    }

    public Jws<Claims> validateToken(String token) {
        SecretKey key = Keys.hmacShaKeyFor(jwtSecretKey.getBytes());

        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);

    }

}