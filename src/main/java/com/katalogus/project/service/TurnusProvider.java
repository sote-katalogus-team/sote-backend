package com.katalogus.project.service;

import com.katalogus.project.entity.Turnus;
import com.katalogus.project.repository.TurnusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnusProvider {

    @Autowired
    TurnusRepository turnusRepository;

    public List<Turnus> getAll() {
        return turnusRepository.findAll();
    }

    public Boolean saveNewTurnus(Turnus turnus) {
        Object response = turnusRepository.save(turnus);
        return response.getClass().equals(Turnus.class);
    }

    public Boolean updateTurnusById(Turnus turnus, Long turnusId) {
        turnus.setId(turnusId);
        Object response = turnusRepository.save(turnus);
        return response.getClass().equals(Turnus.class);
    }
}
