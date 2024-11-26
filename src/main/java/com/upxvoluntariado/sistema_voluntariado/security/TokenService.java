package com.upxvoluntariado.sistema_voluntariado.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.upxvoluntariado.sistema_voluntariado.entity.OSC;
import com.upxvoluntariado.sistema_voluntariado.entity.Voluntario;
import java.time.*;

@Service
public class TokenService {
    
    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(Voluntario voluntario){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            String token = JWT.create().withIssuer("Autenticação_Voluntario_API")
                .withSubject(voluntario.getEmail())
                .withExpiresAt(this.gerarTempoExpirar())
                .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro na autenticação");
        }
    }

    public String gerarTokenOSC(OSC osc){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            String token = JWT.create().withIssuer("Autenticação_Voluntario_API")
                .withSubject(osc.getEmail())
                .withExpiresAt(this.gerarTempoExpirar())
                .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro na autenticação");
        }
    }

    public String validarToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
            .withIssuer("Autenticação_Voluntario_API")
            .build()
            .verify(token)
            .getSubject();
        } catch (JWTVerificationException exception) {
            return null;
        }
    }
    private Instant gerarTempoExpirar(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
