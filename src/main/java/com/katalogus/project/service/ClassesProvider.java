package com.katalogus.project.service;

import com.katalogus.project.entity.Turnus;
import com.katalogus.project.model.Classes;
import com.katalogus.project.repository.EloadasRepository;
import com.katalogus.project.repository.GyakorlatRepository;
import com.katalogus.project.repository.KonzultacioRepository;
import com.katalogus.project.repository.TurnusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassesProvider {

    @Autowired
    TurnusRepository turnusRepository;

    @Autowired
    EloadasRepository eloadasRepository;

    @Autowired
    GyakorlatRepository gyakorlatRepository;

    @Autowired
    KonzultacioRepository konzultacioRepository;


    public Classes getAllByTurnusId(Long turnusId) {
        Classes classes = new Classes();
        classes.setEloadasList(eloadasRepository.findAllByTurnus_id(turnusId));
        classes.setGyakorlatList(gyakorlatRepository.findAllByTurnus_id(turnusId));
        classes.setKonzultacioList(konzultacioRepository.findAllByTurnus_id(turnusId));

        return classes;
    }
}
