package com.katalogus.project.service;

import com.katalogus.project.entity.*;
import com.katalogus.project.model.ClassAttendance;
import com.katalogus.project.model.Classes;
import com.katalogus.project.model.StudentStatistic;
import com.katalogus.project.repository.*;
import com.katalogus.project.utility.AttendancePercentage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<ClassAttendance> getClassesStatisByTurnusId(Long turnusId) {
        List<Student> studentList = studentRepository.findAllByTurnusId(turnusId);
        List<Gyakorlat> gyakorlatList = gyakorlatRepository.findAllByTurnusId(turnusId);
        List<Eloadas> eloadasList = eloadasRepository.findAllByTurnusId(turnusId);
        List<Konzultacio> konzultacioList = konzultacioRepository.findAllByTurnusId(turnusId);
        List<ClassAttendance> classAttendances = new ArrayList<>();

        /*
        Minden órához meg kell számolni hányan vettek rajta rész turnuson belül.
        Az összes listában csak az adott turnushoz tartozó adatok vannak.

        */
        return classAttendances;

    }
}
