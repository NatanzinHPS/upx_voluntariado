package com.upxvoluntariado.sistema_voluntariado.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upxvoluntariado.sistema_voluntariado.dto.InscricaoResponseDTO;
import com.upxvoluntariado.sistema_voluntariado.service.InscricaoService;

@RestController
@RequestMapping("/inscricao")
public class InscricaoController {

    private final InscricaoService inscricaoService;

    public InscricaoController(InscricaoService inscricaoService) {
        this.inscricaoService = inscricaoService;
    }

    @PostMapping("/{voluntarioId}/{oscId}")
    public ResponseEntity<InscricaoResponseDTO> inscrever(
            @PathVariable Long voluntarioId,
            @PathVariable Long oscId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(inscricaoService.inscrever(voluntarioId, oscId));
    }
}