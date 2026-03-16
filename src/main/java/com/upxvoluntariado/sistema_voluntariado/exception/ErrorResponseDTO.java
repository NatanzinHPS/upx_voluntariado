package com.upxvoluntariado.sistema_voluntariado.exception;

import java.time.LocalDateTime;

public record ErrorResponseDTO(
        int status,
        String erro,
        String mensagem,
        LocalDateTime timestamp
) {}