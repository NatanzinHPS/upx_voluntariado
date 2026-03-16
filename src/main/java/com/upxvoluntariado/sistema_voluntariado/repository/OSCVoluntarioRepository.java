package com.upxvoluntariado.sistema_voluntariado.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.upxvoluntariado.sistema_voluntariado.entity.OSCVoluntario;

@Repository
public interface OSCVoluntarioRepository extends JpaRepository<OSCVoluntario, Long> {

    List<OSCVoluntario> findByVoluntarioId(Long voluntarioId);

    List<OSCVoluntario> findByOscId(Long oscId);

    boolean existsByVoluntarioIdAndOscId(Long voluntarioId, Long oscId);
}