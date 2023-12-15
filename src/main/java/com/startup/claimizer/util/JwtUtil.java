package com.startup.claimizer.util;

import com.startup.claimizer.dto.UserDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtil {
    private final String SECRET_KEY = "CLAMIZER";
    private final long ACCESS_TOKEN_VALIDITY = 60 * 60 * 1000;
    private final String TOKEN_HEADER = "Authorization";
    private final String TOKEN_PREFIX = "Bearer ";

    private final JwtParser jwtParser;

    public JwtUtil() {
        this.jwtParser = (JwtParser) Jwts.parser().setSigningKey(SECRET_KEY);
    }

    public String createToken(UserDto userDto) {
        Claims claims = Jwts.claims().subject(userDto.getEmail()).build();
        claims.put("name", userDto.getName());
        claims.put("email", userDto.getEmail());
        claims.put("mobile", userDto.getMobile());
        Date tokenCreatedDate = new Date();
        Date tokenValidatedDate = new Date(tokenCreatedDate.getTime() + TimeUnit.MINUTES.toMillis(ACCESS_TOKEN_VALIDITY));
        return Jwts.builder()
                .claims(claims)
                .expiration(tokenValidatedDate)
                .signWith(SignatureAlgorithm.ES256, SECRET_KEY)
                .compact();
    }
}
