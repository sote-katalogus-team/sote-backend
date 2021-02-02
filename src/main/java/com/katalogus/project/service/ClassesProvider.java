package com.katalogus.project.service;

import com.katalogus.project.entity.Eloadas;
import com.katalogus.project.entity.Gyakorlat;
import com.katalogus.project.entity.Konzultacio;
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

    public Boolean saveNewEloadas(Eloadas eloadas) {
        Object response = eloadasRepository.save(eloadas);
        return response.getClass().equals(Eloadas.class);
    }

    public Boolean saveNewKonzultacio(Konzultacio konzultacio) {
        Object response = konzultacioRepository.save(konzultacio);
        return response.getClass().equals(Konzultacio.class);
    }

    public Boolean saveNewGyakorlat(Gyakorlat gyakorlat) {
        Object response = gyakorlatRepository.save(gyakorlat);
        return response.getClass().equals(Gyakorlat.class);
    }

    public Boolean updateEloadasById(Eloadas eloadas, Long eloadasId) {
        eloadas.setId(eloadasId);
        Object response = eloadasRepository.save(eloadas);
        return response.getClass().equals(Eloadas.class);
    }

    public Boolean updateKonzultacioById(Konzultacio konzultacio, Long konzultacioId) {
        konzultacio.setId(konzultacioId);
        Object response = konzultacioRepository.save(konzultacio);
        return response.getClass().equals(Konzultacio.class);
    }

    public Boolean updateGyakorlatById(Gyakorlat gyakorlat, Long gyakorlatId) {
        gyakorlat.setId(gyakorlatId);
        Object response = gyakorlatRepository.save(gyakorlat);
        return response.getClass().equals(Gyakorlat.class);
    }

    public void deleteEloadasById(Long eloadasId) {
        eloadasRepository.deleteById(eloadasId);
    }

    public void deleteKonzultacioById(Long konzultacioId) {
        konzultacioRepository.deleteById(konzultacioId);
    }

    public void deleteGyakorlatById(Long gyakorlatId) {
        gyakorlatRepository.deleteById(gyakorlatId);
    }
}
