package com.upxvoluntariado.sistema_voluntariado.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upxvoluntariado.sistema_voluntariado.entity.OSC;
import com.upxvoluntariado.sistema_voluntariado.entity.OSCVoluntario;
import com.upxvoluntariado.sistema_voluntariado.entity.Voluntario;
import com.upxvoluntariado.sistema_voluntariado.repository.OSCRepository;
import com.upxvoluntariado.sistema_voluntariado.repository.OSCVoluntarioRepository;
import com.upxvoluntariado.sistema_voluntariado.repository.VoluntarioRepository;
import com.upxvoluntariado.sistema_voluntariado.service.EmailService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/inscricao")
@RequiredArgsConstructor
public class InscricaoController {
    
    @Autowired
    private EmailService emailService;
    @Autowired
    private OSCVoluntarioRepository oscVoluntarioRepository;
    @Autowired
    private VoluntarioRepository voluntarioRepository;
    @Autowired
    private OSCRepository oscRepository;

    @PostMapping("/{voluntarioId}/{oscId}")
    public ResponseEntity<?> inscreverVoluntarioEmOSC(@PathVariable Long voluntarioId, @PathVariable Long oscId){
        OSC osc = oscRepository.findById(oscId).orElseThrow(() -> new RuntimeException("OSC não encontrada!"));
        Voluntario voluntario = voluntarioRepository.findById(voluntarioId).orElseThrow(() -> new RuntimeException("Voluntário não encontrado!"));

        OSCVoluntario oscVoluntario = new OSCVoluntario();
        oscVoluntario.setVoluntario(voluntario);
        oscVoluntario.setOsc(osc);
        
        oscVoluntarioRepository.save(oscVoluntario);
        emailService.enviarEmail(voluntario.getEmail(), "Inscrição feita com sucesso", "Usuário " + voluntario.getNome() + " inscrito na OSC " + osc.getNome() + " com sucesso.");
        return ResponseEntity.ok("Voluntário inscrito na OSC com sucesso!");

    }
}
