package com.upxvoluntariado.sistema_voluntariado.controller;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upxvoluntariado.sistema_voluntariado.entity.Voluntario;
import com.upxvoluntariado.sistema_voluntariado.service.VoluntarioService;

import jakarta.validation.Valid;



@RestController //Indica que esta classe é um controlador REST, lida com requisicoes HTTP.
@RequestMapping("/voluntario") //Identificar que quer cadastrar um voluntario e diferenciar o endpoint da OSC.
public class VoluntarioController {
    private VoluntarioService voluntarioService;

    public VoluntarioController(VoluntarioService voluntarioService) {
        this.voluntarioService = voluntarioService;
    }

    @PostMapping
    List<Voluntario> create(@Valid @RequestBody Voluntario voluntario) {
        return voluntarioService.create(voluntario);
    }

    @GetMapping
    List<Voluntario> list() {
        return voluntarioService.list();
    }

    @PutMapping
    List<Voluntario> update(@Valid @RequestBody Voluntario voluntario) {
        return voluntarioService.uptade(voluntario);
    }

    @DeleteMapping("{id}")
    List<Voluntario> delete(@PathVariable("id") Long id) {
        return voluntarioService.delete(id);
    }
}