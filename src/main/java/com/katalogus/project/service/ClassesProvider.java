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
import java.util.Optional;
import java.util.stream.Stream;

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

    public Classes getAllOriginalByTurnusId(Long turnusId) {
        return Classes.builder()
                .eloadasList(eloadasRepository.findAllByTurnusIdAndPotlasIsFalse(turnusId))
                .gyakorlatList(gyakorlatRepository.findAllByTurnusIdAndPotlasIsFalse(turnusId))
                .konzultacioList(konzultacioRepository.findAllByTurnusIdAndPotlasIsFalse(turnusId))
                .build();
    }

    public List<StudentStatistic> getAllStatistic(Long turnusId) {
        Turnus turnus = turnusRepository.getOne(turnusId);
        List<Student> studentList = studentRepository.findAllByTurnusId(turnusId);
        Classes classes = Classes.builder()
                .eloadasList(eloadasRepository.findAllByTurnusId(turnusId))
                .gyakorlatList(gyakorlatRepository.findAllByTurnusId(turnusId))
                .konzultacioList(konzultacioRepository.findAllByTurnusId(turnusId))
                .build();
        return attendancePercentage.getStudentsStatistics(turnus, studentList, classes);
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

    public void saveClasses(Classes classes) {
        eloadasRepository.saveAll(classes.getEloadasList());
        konzultacioRepository.saveAll(classes.getKonzultacioList());
        gyakorlatRepository.saveAll(classes.getGyakorlatList());
    }

    public List<ClassAttendance> getClassesStatisByTurnusId(Long turnusId) {
        List<Student> studentList = studentRepository.findAllByTurnusId(turnusId);
        List<Gyakorlat> gyakorlatList = gyakorlatRepository.findAllByTurnusId(turnusId);
        List<Eloadas> eloadasList = eloadasRepository.findAllByTurnusId(turnusId);
        List<Konzultacio> konzultacioList = konzultacioRepository.findAllByTurnusId(turnusId);
        List<ClassAttendance> classAttendances = new ArrayList<>();


        for (Gyakorlat gyakorlat : gyakorlatList) {
            classAttendances.add(new ClassAttendance(gyakorlat, szamolGyakorlat(gyakorlat.getId(), studentList)));
        }
        for (Eloadas eloadas : eloadasList) {
            classAttendances.add(new ClassAttendance(eloadas, szamolEloadas(eloadas.getId(), studentList)));
        }
        for (Konzultacio konzultacio : konzultacioList) {
            classAttendances.add(new ClassAttendance(konzultacio, szamolKonzultacio(konzultacio.getId(), studentList)));
        }

        return classAttendances;

    }

    private int szamolGyakorlat(Long id, List<Student> studentList) {
        int counter = 0;
        for (Student student : studentList) {
            int number = (int) student.getGyakorlatList().stream().filter(a -> a.getId() == id).count();
            counter += number;
        }
        return counter;
    }

    private int szamolEloadas(Long id, List<Student> studentList) {
        int counter = 0;
        for (Student student : studentList) {
            int number = (int) student.getEloadasList().stream().filter(a -> a.getId() == id).count();
            counter += number;
        }
        return counter;
    }

    private int szamolKonzultacio(Long id, List<Student> studentList) {
        int counter = 0;
        for (Student student : studentList) {
            int number = (int) student.getKonzultacioList().stream().filter(a -> a.getId() == id).count();
            counter += number;
        }
        return counter;
    }
}
