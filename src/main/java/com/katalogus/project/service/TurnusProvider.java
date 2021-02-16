package com.katalogus.project.service;

import com.katalogus.project.entity.Eloadas;
import com.katalogus.project.entity.Gyakorlat;
import com.katalogus.project.entity.Konzultacio;
import com.katalogus.project.entity.Turnus;
import com.katalogus.project.model.Classes;
import com.katalogus.project.repository.TurnusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Service
public class TurnusProvider {

    @Autowired
    TurnusRepository turnusRepository;

    @Autowired
    ClassesProvider classesProvider;

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

    public boolean duplicateByTurnusId(Long newTurnusId, Long oldTurnusId) {
        Classes oldClasses = classesProvider.getAllOriginalByTurnusId(oldTurnusId);
        List<Eloadas> eloadasList = oldClasses.getEloadasList();
        List<Gyakorlat> gyakorlatList = oldClasses.getGyakorlatList();
        List<Konzultacio> konzultacioList = oldClasses.getKonzultacioList();
        List<Eloadas> newEloadasList = new ArrayList<>();
        List<Gyakorlat> newGyakorlatList = new ArrayList<>();
        List<Konzultacio> newKonzultacioList = new ArrayList<>();
        eloadasList.forEach(eloadas -> {
            newEloadasList.add(Eloadas.builder()
                    .potlas(false)
                    .name(eloadas.getName())
                    .date(eloadas.getDate())
                    .turnusId(newTurnusId)
                    .point(eloadas.getPoint())
                    .build());
        });
        gyakorlatList.forEach(gyakorlat -> {
            newGyakorlatList.add(Gyakorlat.builder()
                    .potlas(false)
                    .name(gyakorlat.getName())
                    .date(gyakorlat.getDate())
                    .turnusId(newTurnusId)
                    .point(gyakorlat.getPoint())
                    .build());
        });
        konzultacioList.forEach(konzultacio -> {
            newKonzultacioList.add(Konzultacio.builder()
                    .potlas(false)
                    .name(konzultacio.getName())
                    .date(konzultacio.getDate())
                    .turnusId(newTurnusId)
                    .point(konzultacio.getPoint())
                    .build());
        });
        Classes newClasses = Classes.builder()
                .gyakorlatList(newGyakorlatList)
                .konzultacioList(newKonzultacioList)
                .eloadasList(newEloadasList)
                .build();
        classesProvider.saveClasses(newClasses);
        Classes savedClasses = classesProvider.getAllByTurnusId(newTurnusId);
        return savedClasses != null;
    }
}
