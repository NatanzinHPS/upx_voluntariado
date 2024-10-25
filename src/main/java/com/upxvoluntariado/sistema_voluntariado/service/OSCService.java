package com.upxvoluntariado.sistema_voluntariado.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.upxvoluntariado.sistema_voluntariado.entity.OSC;
import com.upxvoluntariado.sistema_voluntariado.repository.OSCRepository;

@Service
public class OSCService {
    
    private OSCRepository oscRepository;

    public OSCService(OSCRepository oscRepository) {
        this.oscRepository = oscRepository;
    }

    public List<OSC> create(OSC osc) {
        oscRepository.save(osc);
        return list();
    }

    public List<OSC> list(){
        Sort sort = Sort.by("id").ascending();
        return oscRepository.findAll(sort);
    }

    public List<OSC> update(OSC osc){
        oscRepository.save(osc);        
        return list();
    }

    public List<OSC> delete(Long id){
        oscRepository.deleteById(id);
        return list();
    }
}
