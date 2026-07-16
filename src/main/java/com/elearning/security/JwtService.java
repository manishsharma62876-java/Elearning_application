package com.elearning.security;

import java.util.Date;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Service
public class JwtService {


    private String secretKey = 
            "mySuperSecretKeyForElearningApplicationJWTAuthentication123456";



    // Generate JWT Token
    public String generateToken(String email) {


        return Jwts.builder()

                .setSubject(email)

                .setIssuedAt(new Date())

                .setExpiration(
                    new Date(System.currentTimeMillis() + 1000 * 60 * 60)
                )

                .signWith(
                    SignatureAlgorithm.HS256,
                    secretKey
                )

                .compact();

    }




    // Extract Email from JWT Token
    public String extractUsername(String token) {


        Claims claims = 
                Jwts.parser()

                .setSigningKey(secretKey)

                .parseClaimsJws(token)

                .getBody();


        return claims.getSubject();

    }


}