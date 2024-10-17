package com.upxvoluntariado.sistema_voluntariado.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.upxvoluntariado.sistema_voluntariado.entity.Voluntario;
import com.upxvoluntariado.sistema_voluntariado.repository.VoluntarioRepository;

@Service //Identificar que é um serviço
public class VoluntarioService {
    
    private VoluntarioRepository voluntarioRepository; //Declara o repositorio
    private BCryptPasswordEncoder bCryptPasswordEncoder; //Criptografar a senha do usuario antes de ir para o banco
    
    public VoluntarioService(VoluntarioRepository voluntarioRepository) { //tipo de injecao de dependencia 
        this.voluntarioRepository = voluntarioRepository; 
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder(); //Cria um codificador novo a cada instancia
    }

    public Voluntario salvarVoluntario(Voluntario voluntario) { 
        String senhaCriptografada = bCryptPasswordEncoder.encode(voluntario.getSenha()); //Pegar a senha digitada para criptografar
        voluntario.setSenha(senhaCriptografada); //Setar a senha ja criptografada

        return voluntarioRepository.save(voluntario); //Retornar a senha salva no voluntario
    }
    
}
