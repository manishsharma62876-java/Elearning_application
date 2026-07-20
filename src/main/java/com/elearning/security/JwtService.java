package com.elearning.security;


import java.util.Date;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;



@Service
public class JwtService {


    private static final String SECRET_KEY =
            "mySuperSecretKeyForElearningApplicationJWTAuthentication123456";



    private static final long JWT_EXPIRATION =
            1000 * 60 * 60; // 1 hour




    // Generate JWT Token

    public String generateToken(String email) {


        return Jwts.builder()

                .setSubject(email)

                .setIssuedAt(new Date())

                .setExpiration(
                        new Date(
                                System.currentTimeMillis()
                                + JWT_EXPIRATION
                        )
                )

                .signWith(
                        SignatureAlgorithm.HS256,
                        SECRET_KEY
                )

                .compact();

    }





    // Extract Email from Token

    public String extractUsername(String token) {


        return extractAllClaims(token)
                .getSubject();

    }





    // Validate Token

    public boolean isTokenValid(
            String token,
            String email
    ) {


        String username =
                extractUsername(token);


        return (
                username.equals(email)
                &&
                !isTokenExpired(token)
        );

    }





    // Check Expiration

    private boolean isTokenExpired(String token) {


        return extractAllClaims(token)
                .getExpiration()
                .before(new Date());

    }





    // Extract Claims

    private Claims extractAllClaims(String token) {


        return Jwts.parser()

                .setSigningKey(SECRET_KEY)

                .parseClaimsJws(token)

                .getBody();

    }


}