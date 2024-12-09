package com.upxvoluntariado.sistema_voluntariado.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upxvoluntariado.sistema_voluntariado.entity.OSC;


public interface OSCRepository extends JpaRepository<OSC, Long> {
    Optional<OSC> findByEmail(String email);
    
}
