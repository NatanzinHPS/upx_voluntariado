package com.upxvoluntariado.sistema_voluntariado.dto;

import java.util.Date;

public record RequestCadastroVoluntarioDTO(String nome,String cpf,String email,String telefone,String senha,Date dataNascimento) {
    
}
