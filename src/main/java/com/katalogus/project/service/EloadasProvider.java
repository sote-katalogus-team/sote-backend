package com.katalogus.project.service;

import com.katalogus.project.entity.Eloadas;
import com.katalogus.project.entity.Gyakorlat;
import com.katalogus.project.repository.EloadasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public Boolean deleteEloadasById(Long eloadasId) {
        long before = eloadasRepository.count();
        eloadasRepository.deleteById(eloadasId);
        long after = eloadasRepository.count();
        return before > after;
    }

    public Boolean changeActiveStatusById(Long eloadasId) {
        boolean success = false;
        Optional<Eloadas> optionalEloadas = eloadasRepository.findById(eloadasId);
        if (optionalEloadas.isPresent()) {
            Eloadas eloadas = optionalEloadas.get();
            eloadas.setActive(!eloadas.getActive());
            eloadasRepository.save(eloadas);
            success = true;
        }
        return success;
    }

    public Eloadas getEloadasById(Long eloadasId) {
        return eloadasRepository.findById(eloadasId).get();

    }
}
