package com.upxvoluntariado.sistema_voluntariado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.upxvoluntariado.sistema_voluntariado.entity.Voluntario;

//O extends extende a entidade voluntario do entity e o tipo do ID
public interface VoluntarioRepository extends JpaRepository<Voluntario, Long>{
    Voluntario findByCpf(String cpf);
} 
