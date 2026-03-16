package com.upxvoluntariado.sistema_voluntariado.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upxvoluntariado.sistema_voluntariado.dto.AuthResponseDTO;
import com.upxvoluntariado.sistema_voluntariado.dto.CadastroOSCRequestDTO;
import com.upxvoluntariado.sistema_voluntariado.dto.CadastroVoluntarioRequestDTO;
import com.upxvoluntariado.sistema_voluntariado.dto.LoginRequestDTO;
import com.upxvoluntariado.sistema_voluntariado.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> loginVoluntario(@RequestBody @Valid LoginRequestDTO dto) {
        return ResponseEntity.ok(authService.loginVoluntario(dto));
    }

    @PostMapping("/login/osc")
    public ResponseEntity<AuthResponseDTO> loginOSC(@RequestBody @Valid LoginRequestDTO dto) {
        return ResponseEntity.ok(authService.loginOSC(dto));
    }

    @PostMapping("/cadastro")
    public ResponseEntity<AuthResponseDTO> cadastrarVoluntario(@RequestBody @Valid CadastroVoluntarioRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.cadastrarVoluntario(dto));
    }

    @PostMapping("/cadastro/osc")
    public ResponseEntity<AuthResponseDTO> cadastrarOSC(@RequestBody @Valid CadastroOSCRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.cadastrarOSC(dto));
    }
}