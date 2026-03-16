package com.upxvoluntariado.sistema_voluntariado.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.upxvoluntariado.sistema_voluntariado.dto.AuthResponseDTO;
import com.upxvoluntariado.sistema_voluntariado.dto.CadastroOSCRequestDTO;
import com.upxvoluntariado.sistema_voluntariado.dto.CadastroVoluntarioRequestDTO;
import com.upxvoluntariado.sistema_voluntariado.dto.LoginRequestDTO;
import com.upxvoluntariado.sistema_voluntariado.entity.OSC;
import com.upxvoluntariado.sistema_voluntariado.entity.Voluntario;
import com.upxvoluntariado.sistema_voluntariado.exception.ConflictException;
import com.upxvoluntariado.sistema_voluntariado.exception.UnauthorizedException;
import com.upxvoluntariado.sistema_voluntariado.repository.OSCRepository;
import com.upxvoluntariado.sistema_voluntariado.repository.VoluntarioRepository;
import com.upxvoluntariado.sistema_voluntariado.security.TokenService;

@Service
public class AuthService {

    private final VoluntarioRepository voluntarioRepository;
    private final OSCRepository oscRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public AuthService(VoluntarioRepository voluntarioRepository,
                       OSCRepository oscRepository,
                       PasswordEncoder passwordEncoder,
                       TokenService tokenService) {
        this.voluntarioRepository = voluntarioRepository;
        this.oscRepository = oscRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    public AuthResponseDTO loginVoluntario(LoginRequestDTO dto) {
        Voluntario voluntario = voluntarioRepository.findByEmail(dto.email())
                .orElseThrow(() -> new UnauthorizedException("Email ou senha inválidos"));

        if (!passwordEncoder.matches(dto.senha(), voluntario.getSenha())) {
            throw new UnauthorizedException("Email ou senha inválidos");
        }

        return new AuthResponseDTO(voluntario.getNome(), tokenService.gerarToken(voluntario));
    }

    public AuthResponseDTO loginOSC(LoginRequestDTO dto) {
        OSC osc = oscRepository.findByEmail(dto.email())
                .orElseThrow(() -> new UnauthorizedException("Email ou senha inválidos"));

        if (!passwordEncoder.matches(dto.senha(), osc.getSenha())) {
            throw new UnauthorizedException("Email ou senha inválidos");
        }

        return new AuthResponseDTO(osc.getNome(), tokenService.gerarTokenOSC(osc));
    }

    public AuthResponseDTO cadastrarVoluntario(CadastroVoluntarioRequestDTO dto) {
        if (voluntarioRepository.existsByEmail(dto.email())) {
            throw new ConflictException("Email já cadastrado");
        }
        if (voluntarioRepository.existsByCpf(dto.cpf())) {
            throw new ConflictException("CPF já cadastrado");
        }
        if (voluntarioRepository.existsByTelefone(dto.telefone())) {
            throw new ConflictException("Telefone já cadastrado");
        }

        Voluntario novoVoluntario = new Voluntario();
        novoVoluntario.setNome(dto.nome());
        novoVoluntario.setCpf(dto.cpf());
        novoVoluntario.setEmail(dto.email());
        novoVoluntario.setTelefone(dto.telefone());
        novoVoluntario.setSenha(passwordEncoder.encode(dto.senha()));
        novoVoluntario.setDataNascimento(dto.dataNascimento());

        voluntarioRepository.save(novoVoluntario);

        return new AuthResponseDTO(novoVoluntario.getNome(), tokenService.gerarToken(novoVoluntario));
    }

    public AuthResponseDTO cadastrarOSC(CadastroOSCRequestDTO dto) {
        if (oscRepository.existsByEmail(dto.email())) {
            throw new ConflictException("Email já cadastrado");
        }
        if (oscRepository.existsByCnpj(dto.cnpj())) {
            throw new ConflictException("CNPJ já cadastrado");
        }

        OSC novaOsc = new OSC();
        novaOsc.setNome(dto.nome());
        novaOsc.setCnpj(dto.cnpj());
        novaOsc.setEmail(dto.email());
        novaOsc.setSenha(passwordEncoder.encode(dto.senha()));

        oscRepository.save(novaOsc);

        return new AuthResponseDTO(novaOsc.getNome(), tokenService.gerarTokenOSC(novaOsc));
    }
}