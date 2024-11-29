package com.upxvoluntariado.sistema_voluntariado.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upxvoluntariado.sistema_voluntariado.dto.RequestCadastroOSCDTO;
import com.upxvoluntariado.sistema_voluntariado.dto.RequestCadastroVoluntarioDTO;
import com.upxvoluntariado.sistema_voluntariado.dto.RequestLoginDTO;
import com.upxvoluntariado.sistema_voluntariado.dto.ResponseDTO;
import com.upxvoluntariado.sistema_voluntariado.entity.OSC;
import com.upxvoluntariado.sistema_voluntariado.entity.Voluntario;
import com.upxvoluntariado.sistema_voluntariado.repository.OSCRepository;
import com.upxvoluntariado.sistema_voluntariado.repository.VoluntarioRepository;
import com.upxvoluntariado.sistema_voluntariado.security.TokenService;
import com.upxvoluntariado.sistema_voluntariado.service.EmailService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
	private EmailService emailService;

    private final VoluntarioRepository repository;
    private final OSCRepository oscRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody RequestLoginDTO body){
        Voluntario voluntario = this.repository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        if(passwordEncoder.matches(body.senha(), voluntario.getSenha())){
            String token = this.tokenService.gerarToken(voluntario);
            return ResponseEntity.ok(new ResponseDTO(voluntario.getNome(), token));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/login/osc")
    public ResponseEntity<?> loginosc(@RequestBody RequestLoginDTO body){
        OSC osc = this.oscRepository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("OSC não encontrada"));
        if(passwordEncoder.matches(body.senha(), osc.getSenha())){
            String token = this.tokenService.gerarTokenOSC(osc);
            return ResponseEntity.ok(new ResponseDTO(osc.getNome(), token));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/cadastro")
    public ResponseEntity<?> cadastro(@RequestBody RequestCadastroVoluntarioDTO body){
        Optional<Voluntario> voluntario = this.repository.findByEmail(body.email());
        if(voluntario.isEmpty()){
            Voluntario novoVoluntario = new Voluntario();
            novoVoluntario.setSenha(passwordEncoder.encode(body.senha()));
            novoVoluntario.setCpf(body.cpf());
            novoVoluntario.setEmail(body.email());
            novoVoluntario.setNome(body.nome());
            novoVoluntario.setTelefone(body.telefone());
            novoVoluntario.setDataNascimento(body.dataNascimento());
            
            repository.save(novoVoluntario);
            String token = this.tokenService.gerarToken(novoVoluntario);
            emailService.enviarEmail(novoVoluntario.getEmail(), "Usuário Cadastrado", "Usuário " + novoVoluntario.getNome() + " cadastrado com sucesso.");
            return ResponseEntity.ok(new ResponseDTO(novoVoluntario.getNome(), token));
        }
        
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/cadastro/osc")
    public ResponseEntity<?> cadastroOSC(@RequestBody RequestCadastroOSCDTO body){
        Optional<OSC> osc = this.oscRepository.findByEmail(body.email());
        if(osc.isEmpty()){
            OSC novaOsc = new OSC();
            novaOsc.setSenha(passwordEncoder.encode(body.senha()));
            novaOsc.setCnpj(body.cnpj());
            novaOsc.setEmail(body.email());
            novaOsc.setNome(body.nome());

            oscRepository.save(novaOsc);
            String token = this.tokenService.gerarTokenOSC(novaOsc);
            emailService.enviarEmail(novaOsc.getEmail(), "OSC cadastrada", "teste");
            return ResponseEntity.ok(new ResponseDTO(novaOsc.getNome(), token));
        }

        return ResponseEntity.badRequest().build();
    }
}
