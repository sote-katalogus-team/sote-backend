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

import java.sql.Date;
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

    @Autowired
    AttendancePercentage attendancePercentage;

    @Autowired
    StudentRepository studentRepository;


    public Classes getAllByTurnusId(Long turnusId) {
        return Classes.builder()
                .eloadasList(eloadasRepository.findAllByTurnus_id(turnusId))
                .gyakorlatList(gyakorlatRepository.findAllByTurnus_id(turnusId))
                .konzultacioList(konzultacioRepository.findAllByTurnus_id(turnusId))
                .build();
    }

    public List<StudentStatistic> getAllStatistic(Long turnusId) {
        Turnus turnus = turnusRepository.getOne(turnusId);
        Classes classes = Classes.builder()
                .eloadasList(eloadasRepository.findAllByTurnus_id(turnusId))
                .gyakorlatList(gyakorlatRepository.findAllByTurnus_id(turnusId))
                .konzultacioList(konzultacioRepository.findAllByTurnus_id(turnusId))
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
