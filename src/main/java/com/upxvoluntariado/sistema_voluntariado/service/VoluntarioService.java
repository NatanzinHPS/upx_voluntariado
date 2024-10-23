package com.upxvoluntariado.sistema_voluntariado.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.upxvoluntariado.sistema_voluntariado.entity.Voluntario;
import com.upxvoluntariado.sistema_voluntariado.repository.VoluntarioRepository;

@Service //Identificar que é um serviço
public class VoluntarioService {
    
    private VoluntarioRepository voluntarioRepository; //Declara o repositorio
    
    public VoluntarioService(VoluntarioRepository voluntarioRepository) { //tipo de injecao de dependencia 
        this.voluntarioRepository = voluntarioRepository; 
    }
    public List<Voluntario> create(Voluntario voluntario){
        voluntarioRepository.save(voluntario); //Salvar a senha criptografada no voluntario
        return list();
    }
    public List<Voluntario> list(){
        Sort sort = Sort.by("id").ascending();
        return voluntarioRepository.findAll(sort);  
    }

    public List<Voluntario> uptade(Voluntario voluntario){
        voluntarioRepository.save(voluntario);
        return list();
    }

    public List<Voluntario> delete(Long id){
        voluntarioRepository.deleteById(id);
        return list();
    }
    
}
