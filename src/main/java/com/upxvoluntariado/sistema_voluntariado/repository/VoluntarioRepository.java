package com.upxvoluntariado.sistema_voluntariado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.upxvoluntariado.sistema_voluntariado.entity.Voluntario;
import java.util.Optional;

//O extends extende a entidade voluntario do entity e o tipo do ID
public interface VoluntarioRepository extends JpaRepository<Voluntario, Long>{
    Optional<Voluntario> findByEmail(String email);
} 
