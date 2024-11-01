package com.upxvoluntariado.sistema_voluntariado.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity //Identificar que é uma entidade
@Table(name = "voluntarios") //Nome da tabela
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Voluntario {
    @Id //Mostrar que é um ID
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Tipo auto increment no MySQL
    private Long id;
    
    @NotNull(message = "Nome é obrigatório")
    private String nomeCompleto;

    @Size(min = 11, max = 11, message = "O CPF deve ter exatamente 11 caracteres")
    @Column(columnDefinition = "CHAR(11)")
    @NotNull(message = "CPF é obrigatório")
    private String cpf;

    @Email
    @NotNull(message = "Email é obrigatório")
    private String email;

    @NotNull(message = "Telefone é obrigatório")
    private String telefone;

    @NotNull(message = "Senha é obrigatório")
    private String senha;

    @NotNull(message = "Data de nascimento é obrigatório")
    @Past(message = "Data de nascimento deve ser uma data passada") //Data de nasc tem q ser passada da atual
    @Temporal(TemporalType.DATE) //YYYY-MM-DD
    private Date dataNascimento;

    @Temporal(TemporalType.DATE) 
    private Date dataCadastro;

    @PrePersist //Configura o campo dataCadastro para registrar automaticamente a data e hora no momento do cadastro
    protected void onCreate(){
        this.dataCadastro = new Date();
    }
    
}
