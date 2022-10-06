package com.example.bakeryrecipe.authentication;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class JwtUtils {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${app.jwtSecret}")
    private String jwtSecret;
    @Value("${app.jwtExpirationTime}")
    private int jwtExpirationTime;
    @Value("${app.jwtKey}")
    private String jwtKey;

    private Algorithm getAlgorithm() {
        return Algorithm.HMAC512(jwtSecret);
    }

    public String getToken(HttpServletRequest request){
        return request.getHeader("Authorization");
    }

    public ResponseCookie generateJwtCookie(String username) {
        String jwt = generateTokenFromUsername(username);
        ResponseCookie cookie = ResponseCookie.from(jwtKey, jwt).path("/").maxAge(60 * 60).httpOnly(true).build();
        return cookie;
    }

    public ResponseCookie getCleanJwtCookie() {
        ResponseCookie cookie = ResponseCookie.from(jwtKey, null).path("/").build();
        return cookie;
    }

    public String getUserNameFromJwtToken(String token) {

        JWTVerifier verifier = JWT.require(getAlgorithm()).build();

        DecodedJWT jwt = verifier.verify(token);

        if (jwt != null) {
            return jwt.getSubject();
        }
        return null;
    }
    public boolean validateJwtToken(String authToken) {

        try {
            JWT.require(getAlgorithm()).build().verify(authToken);
            return true;
        } catch (JWTVerificationException e) {
            logger.warn("Failed token {}: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }

    public String generateTokenFromUsername(String username) {

        Date expireTime = new Date(System.currentTimeMillis() + jwtExpirationTime);

        return JWT.create().withSubject(username).withIssuedAt(new Date()).withExpiresAt(expireTime).sign(getAlgorithm());
    }
}
