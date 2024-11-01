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
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "oscs")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OSC {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Nome é obrigatório")
    private String nome;

    @Size(min = 14, max = 14, message = "O CNPJ deve ter exatamente 14 caracteres")
    @Column(columnDefinition = "CHAR(14)")
    @NotNull(message = "CNPJ é obrigatório")
    private String cnpj;

    @Email
    @NotNull(message = "Email é obrigatório")
    private String email;

    @NotNull(message = "Senha é obrigatório")
    private String senha;

    @Temporal(TemporalType.DATE) 
    private Date dataCadastro;

    @PrePersist //Configura o campo dataCadastro para registrar automaticamente a data e hora no momento do cadastro
    protected void onCreate(){
        this.dataCadastro = new Date();
    }
}
