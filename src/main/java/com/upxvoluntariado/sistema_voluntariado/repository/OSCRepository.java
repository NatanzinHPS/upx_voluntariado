package com.upxvoluntariado.sistema_voluntariado.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.upxvoluntariado.sistema_voluntariado.entity.OSC;

@Repository
public interface OSCRepository extends JpaRepository<OSC, Long> {

    Optional<OSC> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByCnpj(String cnpj);
}