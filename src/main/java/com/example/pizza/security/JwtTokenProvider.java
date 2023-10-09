package com.example.pizza.security;

import com.example.pizza.problems.BadRequestException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;


@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    @Value("${pizza.secret}")
    private String secret;

    @Value("${pizza.expiration}")
    private String expiration;
    private static Key mSecret =  null;
    @PostConstruct
    private void init(){
        mSecret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    public String generateToken(String username) {
        var currentDate = new Date();
        var expiresDate = new Date(currentDate.getTime() + Long.parseLong(expiration));

        return Jwts.builder()
                .setSubject(username)
                .setExpiration(expiresDate)
                .setIssuedAt(currentDate)
                .signWith(mSecret, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String jwt) {
        try {
            Jwts
                    .parserBuilder()
                    .setSigningKey(mSecret)
                    .build()
                    .parseClaimsJws(jwt);
        } catch (ExpiredJwtException e) {
            throw new BadRequestException("Token", "Expired");
        } catch (MalformedJwtException e) {
            throw new BadRequestException("Token", "Invalid");
        } catch (JwtException e) {
            throw new BadRequestException("Token", "Exception " + e.getMessage());
        }
        return true;
    }

    public String getUsernameFromToken(String jwt) {
        return Jwts
                .parserBuilder()
                .setSigningKey(mSecret)
                .build()
                .parseClaimsJws(jwt)
                .getBody()
                .getSubject();

    }


}
