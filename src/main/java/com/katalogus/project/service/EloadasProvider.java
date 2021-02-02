package com.katalogus.project.service;

import com.katalogus.project.entity.Eloadas;
import com.katalogus.project.repository.EloadasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EloadasProvider {

    @Autowired
    EloadasRepository eloadasRepository;

    public Boolean saveNewEloadas(Eloadas eloadas) {
        Object response = eloadasRepository.save(eloadas);
        return response.getClass().equals(Eloadas.class);
    }

    public Boolean updateEloadasById(Eloadas eloadas, Long eloadasId) {
        eloadas.setId(eloadasId);
        Object response = eloadasRepository.save(eloadas);
        return response.getClass().equals(Eloadas.class);
    }

    public void deleteEloadasById(Long eloadasId) {
        eloadasRepository.deleteById(eloadasId);
    }
}
