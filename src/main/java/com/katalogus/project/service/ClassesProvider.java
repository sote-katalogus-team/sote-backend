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
        Classes classes = new Classes();
        classes.setEloadasList(eloadasRepository.findAllByTurnus_id(turnusId));
        classes.setGyakorlatList(gyakorlatRepository.findAllByTurnus_id(turnusId));
        classes.setKonzultacioList(konzultacioRepository.findAllByTurnus_id(turnusId));

        return classes;
    }

    public List<StudentStatistic> getAllStatistic(Long turnusId) {
        Turnus turnus = turnusRepository.getOne(turnusId);
        return attendancePercentage.getStudentsStatistics(turnus, studentRepository.findAll(), eloadasRepository.findAll(), gyakorlatRepository.findAll(), konzultacioRepository.findAll());
    }
}
