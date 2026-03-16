package com.upxvoluntariado.sistema_voluntariado.security;

import java.io.IOException;
import java.util.Collections;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.upxvoluntariado.sistema_voluntariado.entity.OSC;
import com.upxvoluntariado.sistema_voluntariado.entity.Voluntario;
import com.upxvoluntariado.sistema_voluntariado.repository.OSCRepository;
import com.upxvoluntariado.sistema_voluntariado.repository.VoluntarioRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final VoluntarioRepository voluntarioRepository;
    private final OSCRepository oscRepository;

    public SecurityFilter(TokenService tokenService,
                          VoluntarioRepository voluntarioRepository,
                          OSCRepository oscRepository) {
        this.tokenService = tokenService;
        this.voluntarioRepository = voluntarioRepository;
        this.oscRepository = oscRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String token = recuperarToken(request);

        if (token != null) {
            String email = tokenService.validarToken(token);
            TipoUsuario tipo = tokenService.extrairTipo(token);

            if (email != null && tipo != null) {
                autenticar(email, tipo);
            }
        }

        filterChain.doFilter(request, response);
    }

    private void autenticar(String email, TipoUsuario tipo) {
        Object principal = null;
        String role = null;

        if (tipo == TipoUsuario.VOLUNTARIO) {
            Voluntario voluntario = voluntarioRepository.findByEmail(email).orElse(null);
            if (voluntario != null) {
                principal = voluntario;
                role = "ROLE_VOLUNTARIO";
            }
        } else if (tipo == TipoUsuario.OSC) {
            OSC osc = oscRepository.findByEmail(email).orElse(null);
            if (osc != null) {
                principal = osc;
                role = "ROLE_OSC";
            }
        }

        if (principal != null) {
            var authorities = Collections.singletonList(new SimpleGrantedAuthority(role));
            var authentication = new UsernamePasswordAuthenticationToken(principal, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }

    private String recuperarToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) return null;
        return authHeader.substring(7);
    }
}