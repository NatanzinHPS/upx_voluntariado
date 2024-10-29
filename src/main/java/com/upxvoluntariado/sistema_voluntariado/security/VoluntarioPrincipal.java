package com.upxvoluntariado.sistema_voluntariado.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.upxvoluntariado.sistema_voluntariado.entity.Voluntario;
import lombok.Getter;

@Getter
public class VoluntarioPrincipal {
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    
    private VoluntarioPrincipal(Voluntario voluntario){
        this.username = voluntario.getCpf();
        this.password = voluntario.getSenha();

        this.authorities = voluntario.getRoles().stream().map(role -> {
            return new SimpleGrantedAuthority("ROLE ".concat(role.getName()));
        }).collect(Collectors.toList());
    }

    public static VoluntarioPrincipal create(Voluntario voluntario){
        return new VoluntarioPrincipal(voluntario);
    }
}
