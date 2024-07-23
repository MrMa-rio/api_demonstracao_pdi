package com.criar.pdi.demonstracao.services;

import com.auth0.jwt.algorithms.Algorithm;
import com.criar.pdi.demonstracao.models.User.User;
import org.springframework.beans.factory.annotation.Value;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.services.TokenServices.secretKey}")
    private String secretKey;

    public String generate(User user){
        try {
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            return JWT.create()
                    .withIssuer("API Demonstração")
                    .withSubject(user.getUsername())
                    .withClaim("email",user.getUsername())
                    .withClaim("id",user.getCommonDTO().ID())
                    .withExpiresAt(dataExpirate())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw  new RuntimeException("ERRO AO GERAR TOKEN",exception);
        }
    }

    public String getSubject(String token){

        try {
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            return JWT.require(algorithm)
                    .withIssuer("API Demonstração")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception){
            throw  new RuntimeException(exception.getMessage());
        }
    }

    public Claim getClaim(String claim, String token){

        try {
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            return JWT.require(algorithm)
                    .withIssuer("API Demonstração")
                    .build()
                    .verify(token)
                    .getClaim(claim);
        } catch (JWTVerificationException exception){
            throw  new RuntimeException(exception.getMessage());
        }
    }

    public Instant dataExpirate(){
        return LocalDateTime.now()
                .plusHours(2)
                .toInstant(ZoneOffset.of("-03:00"));
    }
}
