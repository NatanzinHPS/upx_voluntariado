package com.upxvoluntariado.sistema_voluntariado.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity //Identificar que é uma entidade
@Table(name = "oscs_voluntarios") //Nome da tabela
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OSCVoluntario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "voluntario_id", nullable = false)
    private Voluntario voluntario;

    @ManyToOne
    @JoinColumn(name = "osc_id", nullable = false)
    private OSC osc;

    @Temporal(TemporalType.DATE)
    private Date dataInscricao;

     @PrePersist 
    protected void onCreate(){
        this.dataInscricao = new Date();
    }
}
