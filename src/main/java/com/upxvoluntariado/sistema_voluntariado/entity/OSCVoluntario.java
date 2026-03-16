package com.upxvoluntariado.sistema_voluntariado.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
        name = "oscs_voluntarios",
        uniqueConstraints = @UniqueConstraint(columnNames = {"voluntario_id", "osc_id"})
)
public class OSCVoluntario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "voluntario_id", nullable = false)
    private Voluntario voluntario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "osc_id", nullable = false)
    private OSC osc;

    @Column(nullable = false, updatable = false)
    private LocalDate dataInscricao;

    public OSCVoluntario() {}

    @PrePersist
    protected void onCreate() {
        this.dataInscricao = LocalDate.now();
    }

    public Long getId() { return id; }

    public Voluntario getVoluntario() { return voluntario; }
    public void setVoluntario(Voluntario voluntario) { this.voluntario = voluntario; }

    public OSC getOsc() { return osc; }
    public void setOsc(OSC osc) { this.osc = osc; }

    public LocalDate getDataInscricao() { return dataInscricao; }
}