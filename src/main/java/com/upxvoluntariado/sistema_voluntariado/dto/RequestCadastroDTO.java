package com.upxvoluntariado.sistema_voluntariado.dto;

import java.util.Date;

public record RequestCadastroDTO(String nome,String cpf,String email,String telefone,String senha,Date dataNascimento) {
    
}
