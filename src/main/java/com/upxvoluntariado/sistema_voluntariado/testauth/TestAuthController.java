package com.upxvoluntariado.sistema_voluntariado.testauth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "teste")
public class TestAuthController {

    @GetMapping
    public String teste(){
        return "Autorizado";
    }
}