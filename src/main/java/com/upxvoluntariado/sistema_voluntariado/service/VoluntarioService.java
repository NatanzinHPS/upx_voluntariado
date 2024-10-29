package com.upxvoluntariado.sistema_voluntariado.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.upxvoluntariado.sistema_voluntariado.entity.Voluntario;
import com.upxvoluntariado.sistema_voluntariado.repository.VoluntarioRepository;

@Service //Identificar que é um serviço
public class VoluntarioService {
    
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    private VoluntarioRepository voluntarioRepository; //Declara o repositorio
    
    public VoluntarioService(VoluntarioRepository voluntarioRepository) { //tipo de injecao de dependencia 
        this.voluntarioRepository = voluntarioRepository; 
    }
    public Voluntario create(Voluntario voluntario){
        Voluntario existVoluntario = voluntarioRepository.findByCpf(voluntario.getCpf());

        if (existVoluntario != null) {
            throw new Error("Usuário já existe");
        }
        voluntario.setSenha(passwordEncoder().encode(voluntario.getSenha()));
        Voluntario criadoVoluntario = voluntarioRepository.save(voluntario); 
        return criadoVoluntario;
    }    
}
