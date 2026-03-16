package com.upxvoluntariado.sistema_voluntariado.dto;

import java.time.LocalDate;

public record InscricaoResponseDTO(
        Long id,
        String nomeVoluntario,
        String nomeOSC,
        LocalDate dataInscricao
) {}