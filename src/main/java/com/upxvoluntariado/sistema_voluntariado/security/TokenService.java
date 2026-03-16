package com.upxvoluntariado.sistema_voluntariado.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.upxvoluntariado.sistema_voluntariado.entity.OSC;
import com.upxvoluntariado.sistema_voluntariado.entity.Voluntario;

@Service
public class TokenService {

    private static final String ISSUER = "sistema-voluntariado-api";
    private static final String CLAIM_TIPO = "tipo";

    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(Voluntario voluntario) {
        return gerarJWT(voluntario.getEmail(), TipoUsuario.VOLUNTARIO);
    }

    public String gerarTokenOSC(OSC osc) {
        return gerarJWT(osc.getEmail(), TipoUsuario.OSC);
    }

    public String validarToken(String token) {
        try {
            return JWT.require(Algorithm.HMAC256(secret))
                    .withIssuer(ISSUER)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            return null;
        }
    }

    public TipoUsuario extrairTipo(String token) {
        try {
            DecodedJWT decoded = JWT.require(Algorithm.HMAC256(secret))
                    .withIssuer(ISSUER)
                    .build()
                    .verify(token);
            return TipoUsuario.valueOf(decoded.getClaim(CLAIM_TIPO).asString());
        } catch (Exception e) {
            return null;
        }
    }

    private String gerarJWT(String subject, TipoUsuario tipo) {
        try {
            return JWT.create()
                    .withIssuer(ISSUER)
                    .withSubject(subject)
                    .withClaim(CLAIM_TIPO, tipo.name())
                    .withExpiresAt(gerarExpiracao())
                    .sign(Algorithm.HMAC256(secret));
        } catch (JWTCreationException e) {
            throw new RuntimeException("Erro ao gerar token", e);
        }
    }

    private Instant gerarExpiracao() {
        return LocalDateTime.now()
                .plusHours(2)
                .toInstant(ZoneOffset.of("-03:00"));
    }
}