package com.startup.claimizer.util;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.startup.claimizer.dto.UserDto;
import com.startup.claimizer.dto.UserSessionData;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Component
public abstract class JwtUtil {
    @Value("${jwt.secret}")
    private static  String SECRET_KEY = "ClaimizerClaimizerClaimizerClaimizerClaimizer";
    private static final long ACCESS_TOKEN_VALIDITY = 15 * 60 * 1000;
    private static final String TOKEN_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";

    public static String createToken(UserDto userDto) {
        Claims claims = Jwts.claims().setSubject("userData");
        claims.put("name", userDto.getName());
        claims.put("email", userDto.getEmail());
        claims.put("mobile", userDto.getMobile());
        Date tokenCreatedDate = new Date();
        Date tokenValidatedDate = new Date(tokenCreatedDate.getTime() + TimeUnit.MILLISECONDS.toMillis(ACCESS_TOKEN_VALIDITY));
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(tokenValidatedDate)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }


    public static Boolean validateToken(String token) {
        if (!isTokenExpired(token)) {
            return true;
        } else {
            throw new TokenExpiredException("Token Expired. please Login First", null);
        }
    }

    public static UserSessionData extractUserData(String token) {
        UserSessionData userSessionData = new UserSessionData();
        userSessionData.setEmail(extractAllClaims(token).get("email").toString());
        userSessionData.setName(extractAllClaims(token).get("name").toString());
        userSessionData.setMobile(extractAllClaims(token).get("mobile").toString());
        return userSessionData;
    }

    public static Date extractExpirationDate(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private static <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private static Claims extractAllClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    private static Boolean isTokenExpired(String token) {
        final Date expiration = extractExpirationDate(token);
        return expiration.before(new Date());
    }

}
