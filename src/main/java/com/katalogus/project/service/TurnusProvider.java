package com.katalogus.project.service;

import com.katalogus.project.entity.Turnus;
import com.katalogus.project.repository.TurnusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.DatabaseMetaData;
import java.time.Year;
import java.util.Date;
import java.util.List;

@Service
public class TurnusProvider {

    @Autowired
    TurnusRepository turnusRepository;

    public List<Turnus> getAll() {
        return turnusRepository.findAll();
    }

    public List<Turnus> getAllByYear() {
        Integer year = Year.now().getValue();
        return turnusRepository.findAllByYear(year);
    }

    public Boolean saveNewTurnus(Turnus turnus) {
        turnus.setCombinedName(turnus.getName() + "/" + turnus.getYear().toString());
        Object response = turnusRepository.save(turnus);
        return response.getClass().equals(Turnus.class);
    }

    public Boolean updateTurnusById(Turnus turnus, Long turnusId) {
        turnus.setId(turnusId);
        Object response = turnusRepository.save(turnus);
        return response.getClass().equals(Turnus.class);
    }

    public Boolean deleteTurnusById(Long turnusId) {
        long before = turnusRepository.count();
        turnusRepository.deleteById(turnusId);
        long after = turnusRepository.count();
        return before > after;
    }
}
