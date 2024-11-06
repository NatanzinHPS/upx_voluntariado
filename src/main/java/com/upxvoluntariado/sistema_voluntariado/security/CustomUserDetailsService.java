package com.upxvoluntariado.sistema_voluntariado.security;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.upxvoluntariado.sistema_voluntariado.entity.Voluntario;
import com.upxvoluntariado.sistema_voluntariado.repository.VoluntarioRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private VoluntarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Voluntario voluntario = this.repository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Voluntário não encontrado!"));
        return new org.springframework.security.core.userdetails.User(voluntario.getEmail(), voluntario.getSenha(), new ArrayList<>());
    }
    
}
