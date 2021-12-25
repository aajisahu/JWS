package com.springbootrest.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtil {

    @Value("${app.secret}")
    private String secret;

    // 6. validate username in token and database, and expiration date
    public boolean isTokenExp(String token, String username){
        String tokenUserName= getUserName(token);
        return (username.equals(tokenUserName) && ! isTokenExp(token));

    }


    // 5. Validate Exp Date
    public boolean isTokenExp(String token){
        Date expDate= getExpDate(token);
        return expDate.before(new Date(System.currentTimeMillis()));
    }


    // 4. Read Subject/Username
    public String getUserName(String token){
        return getClaims(token).getSubject();
    }


    // 3. Read Exp Date
    public Date getExpDate(String token){
        return getClaims(token).getExpiration();
    }

    // 2. Read Cleaims (Read token data)
    public Claims getClaims(String token)
    {
        return Jwts.parser().setSigningKey(secret.getBytes())
                .parseClaimsJws(token)
                .getBody();
    }

    // 1. Generate Token
    public String generateToken(String subject){
        return Jwts.builder()
                .setSubject(subject)
                .setIssuer("AshishITech")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMinutes(15)))
                .signWith(SignatureAlgorithm.HS512, secret.getBytes())
                .compact();

    }

}
