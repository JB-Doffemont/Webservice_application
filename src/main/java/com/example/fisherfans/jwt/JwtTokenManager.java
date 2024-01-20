package com.example.fisherfans.jwt;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;

@Component
// TokenManager is used to create and validate the token
public class JwtTokenManager implements Serializable {
    private int tokenValidity = 5 * 60 * 60;
    private String jwtSecretString = "jdfhfsjkdhfsjkdngbsjkbnjksdbvjksbdbsdjkbsjkdbf";
    byte[] jwtSecretBytes = jwtSecretString.getBytes();
    SecretKey jwtSecret = new SecretKeySpec(jwtSecretBytes, 0, jwtSecretBytes.length, "HmacSHA256");

    // generateToken generates a token for the user with the given username and
    // password
    public String generateJwtToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder().setClaims(claims).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + tokenValidity * 1000))
                .signWith(jwtSecret).compact();
    }

    // validate token and return username from token
    public Boolean validateJwtToken(String token, UserDetails userDetails) {
        String username = getUsernameFromToken(token);
        Claims claims = Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(token).getBody();
        Boolean isTokenExpired = claims.getExpiration().before(new Date(0));
        return (username.equals(userDetails.getUsername()) && !isTokenExpired);
    }

    String getUsernameFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(token).getBody().getSubject();
    }

    // get username from token allows us to get the username from the token
    // and use it to load the user details from the database
    public String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

}
