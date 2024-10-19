package com.upxvoluntariado.sistema_voluntariado.entity;

import java.util.Date;

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

@Entity //Identificar que é uma entidade
@Table(name = "voluntarios") //Nome da tabela
public class Voluntario {
    
    @Id //Mostrar que é um ID
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Tipo auto increment no MySQL
    private Long id;
    @NotNull(message = "Nome é obrigatório")
    private String nome;

    @Email
    @NotNull(message = "Email é obrigatório")
    private String email;

    @NotNull(message = "Senha é obrigatório")
    private String senha;

    @NotNull(message = "Telefone é obrigatório")
    private String telefone;

    @NotNull(message = "Data de nascimento é obrigatório")
    @Past(message = "Data de nascimento deve ser uma data passada") //Data de nasc tem q ser passada da atual
    @Temporal(TemporalType.DATE) //YYYY-MM-DD
    private Date dataNascimento;

    @Temporal(TemporalType.TIMESTAMP) //YYYY-MM-DD hh-mm-ss
    private Date dataCadastro;

    @PrePersist //Configura o campo dataCadastro para registrar automaticamente a data e hora no momento do cadastro
    protected void onCreate(){
        this.dataCadastro = new Date();
    }
    
    //Get e Set basico
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Date getDataNascimento() {
        return dataNascimento; 
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento; 
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
    
}
