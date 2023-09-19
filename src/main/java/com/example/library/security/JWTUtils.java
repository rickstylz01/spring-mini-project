package com.example.library.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.logging.Logger;

@Service
public class JWTUtils {
    Logger logger = Logger.getLogger(JWTUtils.class.getName());

    @Value("${jwt-secret}")
    private String jwtSecret;

    @Value("${jwt-expiration-ms}")
    private int jwtExpMs;

    /**
     * Generates a JSON Web Token for the provided user details.
     * @param myUserDetails The user details used to generate the JWT
     * @return The JWT token as a {@link String}
     */
    public String generateJwtToken(MyUserDetails myUserDetails) {
        return Jwts.builder()
                .setSubject(myUserDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpMs))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    /**
     * Retrieves the username from a JSON Web Token (JWT)
     * @param token The JWT token from which to extract the username
     * @return The username as a {@link String} obtained from the JWT
     */
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(jwtSecret)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
