package com.upxvoluntariado.sistema_voluntariado.service;

import org.springframework.stereotype.Service;

import com.upxvoluntariado.sistema_voluntariado.dto.InscricaoResponseDTO;
import com.upxvoluntariado.sistema_voluntariado.entity.OSC;
import com.upxvoluntariado.sistema_voluntariado.entity.OSCVoluntario;
import com.upxvoluntariado.sistema_voluntariado.entity.Voluntario;
import com.upxvoluntariado.sistema_voluntariado.exception.ConflictException;
import com.upxvoluntariado.sistema_voluntariado.exception.NotFoundException;
import com.upxvoluntariado.sistema_voluntariado.repository.OSCRepository;
import com.upxvoluntariado.sistema_voluntariado.repository.OSCVoluntarioRepository;
import com.upxvoluntariado.sistema_voluntariado.repository.VoluntarioRepository;

@Service
public class InscricaoService {

    private final OSCVoluntarioRepository oscVoluntarioRepository;
    private final VoluntarioRepository voluntarioRepository;
    private final OSCRepository oscRepository;

    public InscricaoService(OSCVoluntarioRepository oscVoluntarioRepository,
                            VoluntarioRepository voluntarioRepository,
                            OSCRepository oscRepository) {
        this.oscVoluntarioRepository = oscVoluntarioRepository;
        this.voluntarioRepository = voluntarioRepository;
        this.oscRepository = oscRepository;
    }

    public InscricaoResponseDTO inscrever(Long voluntarioId, Long oscId) {
        Voluntario voluntario = voluntarioRepository.findById(voluntarioId)
                .orElseThrow(() -> new NotFoundException("Voluntário não encontrado"));

        OSC osc = oscRepository.findById(oscId)
                .orElseThrow(() -> new NotFoundException("OSC não encontrada"));

        if (oscVoluntarioRepository.existsByVoluntarioIdAndOscId(voluntarioId, oscId)) {
            throw new ConflictException("Voluntário já inscrito nesta OSC");
        }

        OSCVoluntario inscricao = new OSCVoluntario();
        inscricao.setVoluntario(voluntario);
        inscricao.setOsc(osc);

        oscVoluntarioRepository.save(inscricao);

        return new InscricaoResponseDTO(
                inscricao.getId(),
                voluntario.getNome(),
                osc.getNome(),
                inscricao.getDataInscricao()
        );
    }
}