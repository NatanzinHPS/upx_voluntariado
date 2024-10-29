package com.upxvoluntariado.sistema_voluntariado.security;

import java.io.IOException;
import java.util.Base64;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.upxvoluntariado.sistema_voluntariado.entity.Voluntario;
import com.upxvoluntariado.sistema_voluntariado.repository.VoluntarioRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomBasicAuthenticationFilter extends OncePerRequestFilter{

    private static final String AUTHORIZATION = "Authorization";
    private static final String BASIC = "Basic ";
    private final VoluntarioRepository voluntarioRepository;
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (isBasicAuthentication(request)) {
            String[] credentials = decodeBase64(getHeader(request).replace(BASIC, "")).split(":");

            String username = credentials[0];
            String password = credentials[1];

            Voluntario voluntario = voluntarioRepository.findByCpf(username);

            if (voluntario == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Usuário não existe!");
                return;
            }

            boolean valid = checkPassword(voluntario.getSenha(), password);

            if (!valid) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Senha incorreta!");
                return;   
            }

            setAuthentication(voluntario);
        }
    }
    
    private void setAuthentication(Voluntario voluntario) {
        Authentication authentication = createAuthenticationToken(voluntario);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private Authentication createAuthenticationToken(Voluntario voluntario) {
        VoluntarioPrincipal voluntarioPrincipal = VoluntarioPrincipal.create(voluntario);
        return new UsernamePasswordAuthenticationToken(voluntarioPrincipal, null, voluntarioPrincipal.getAuthorities());
    }

    private boolean checkPassword(String voluntarioSenha, String loginSenha) {
        return passwordEncoder().matches(voluntarioSenha, loginSenha);
    }

    private String decodeBase64(String base64){
        byte[] decodeBytes = Base64.getDecoder().decode(base64);
        return new String(decodeBytes);
    }

    private boolean isBasicAuthentication(HttpServletRequest request){
        String header = getHeader(request);
        return header != null && header.startsWith(BASIC);
    }

    private String getHeader(HttpServletRequest request) {
        return request.getHeader(AUTHORIZATION);
    }
}
