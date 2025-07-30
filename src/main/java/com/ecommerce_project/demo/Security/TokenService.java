package com.ecommerce_project.demo.Security;


import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.ecommerce_project.demo.Entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


@Service
public class TokenService {

    @Value("${JWT_SECRET:defaultSecretKey}")
    private String secretKey;

    public String generateToken(User user) {
        try{

            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            String token = JWT.create()
                    .withIssuer("E-commerce API")
                    .withSubject(user.getUsername())
                    .withClaim("role", user.getRole().name())
                    .withExpiresAt(genExpirationTime())
                    .sign(algorithm);
            return token;
        }

        catch (JWTCreationException e) {
            throw new RuntimeException("Error generating token: " + e.getMessage());
        }


}

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            return JWT.require(algorithm)
                    .withIssuer("E-commerce API")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            return "";
        }
    }

    private Instant genExpirationTime() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
