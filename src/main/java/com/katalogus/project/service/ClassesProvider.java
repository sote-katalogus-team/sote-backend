package com.katalogus.project.service;

import com.katalogus.project.entity.Eloadas;
import com.katalogus.project.entity.Gyakorlat;
import com.katalogus.project.entity.Konzultacio;
import com.katalogus.project.entity.Turnus;
import com.katalogus.project.model.Classes;
import com.katalogus.project.model.StudentStatistic;
import com.katalogus.project.repository.*;
import com.katalogus.project.utility.AttendancePercentage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
loadas/1
    @Autowired
    AttendancePercentage attendancePercentage;

    @Autowired
    StudentRepository studentRepository;


    public Classes getAllByTurnusId(Long turnusId) {
        return Classes.builder()
                .eloadasList(eloadasRepository.findAllByTurnusId(turnusId))
                .gyakorlatList(gyakorlatRepository.findAllByTurnusId(turnusId))
                .konzultacioList(konzultacioRepository.findAllByTurnusId(turnusId))
                .build();
    }

    public List<StudentStatistic> getAllStatistic(Long turnusId) {
        Turnus turnus = turnusRepository.getOne(turnusId);
        Classes classes = Classes.builder()
                .eloadasList(eloadasRepository.findAllByTurnusId(turnusId))
                .gyakorlatList(gyakorlatRepository.findAllByTurnusId(turnusId))
                .konzultacioList(konzultacioRepository.findAllByTurnusId(turnusId))
                .build();
        return attendancePercentage.getStudentsStatistics(turnus, studentRepository.findAll(), classes);
    }

    public Classes getClassesByDate(Date date) {
        return Classes.builder()
                .eloadasList(eloadasRepository.findByDate(date))
                .konzultacioList(konzultacioRepository.findByDate(date))
                .gyakorlatList(gyakorlatRepository.findByDate(date))
                .build();
    }

    public Classes getAllClasses() {
        return Classes.builder()
                .eloadasList(eloadasRepository.findAll())
                .gyakorlatList(gyakorlatRepository.findAll())
                .konzultacioList(konzultacioRepository.findAll())
                .build();
    }
}
