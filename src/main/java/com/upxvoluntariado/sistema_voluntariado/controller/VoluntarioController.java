package com.upxvoluntariado.sistema_voluntariado.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upxvoluntariado.sistema_voluntariado.entity.Voluntario;
import com.upxvoluntariado.sistema_voluntariado.service.VoluntarioService;

@RestController //Indica que esta classe é um controlador REST, lida com requisicoes HTTP.
@RequestMapping("/voluntarios") //Identificar que quer cadastrar um voluntario e diferenciar o endpoint da OSC.
public class VoluntarioController {

    private VoluntarioService voluntarioService; //Declara o servico

    public VoluntarioController(VoluntarioService voluntarioService) { //Injetar a dependencia
        this.voluntarioService = voluntarioService;
    }

    @PostMapping //O metodo vai responder requisicao HTTP POST.(Criando um novo voluntario)
    public ResponseEntity<Voluntario> cadastrarVoluntario(@RequestBody Voluntario voluntario){ //Request body vai converter o corpo da requisicao(JSON) em um objeto da classe voluntario.
        Voluntario novoVoluntario = voluntarioService.salvarVoluntario(voluntario); //Chama o metodo de criptografar a senha.
        
        return ResponseEntity.ok(novoVoluntario); //Retorna um OK e os dados
    }
}