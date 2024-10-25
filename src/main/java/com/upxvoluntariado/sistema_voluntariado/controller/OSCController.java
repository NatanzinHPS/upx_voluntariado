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

import com.upxvoluntariado.sistema_voluntariado.entity.OSC;
import com.upxvoluntariado.sistema_voluntariado.service.OSCService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/OSC")
public class OSCController {
    private OSCService oscService;

    public OSCController(OSCService oscService) {
        this.oscService = oscService;
    }

    @PostMapping
    List<OSC> create(@Valid @RequestBody OSC osc){
        return oscService.create(osc);
    }

    @GetMapping    
    List<OSC> list(){
        return oscService.list();
    }

    @PutMapping
    List<OSC> update(@Valid @RequestBody OSC osc){
        return oscService.update(osc);
    }

    @DeleteMapping("{id}")
    List<OSC> delete(@PathVariable("id") Long id){
        return oscService.delete(id);
    }
}
