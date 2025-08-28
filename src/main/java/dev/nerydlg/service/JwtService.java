package dev.nerydlg.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class JwtService {

    public static final String SECRET = "TW9sYS1BcHBhLUZpb25hLVNvbl9MYXNfTWVqb3Jlcwo=";
    private static final long MAX_SESSION_TIME = 60 * 60 * 1000;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getPayload();
    }

    private boolean isTokenStillValid(String token) {
        Date expiration = extractExpiration(token);
        return expiration != null && expiration.after(new Date());
    }

    public boolean isValidToken(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return username != null
                && isTokenStillValid(token);
    }

    public String GenerateToken(String username){
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims, String username) {
        return Jwts.builder()
                .claims(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + MAX_SESSION_TIME))
                .signWith(getSigningKey())
                .compact();
    }

    private Key getSigningKey() {
        byte[] bytes = Base64.getDecoder().decode(SECRET);
        return Keys.hmacShaKeyFor(bytes);
    }
}