package com.katalogus.project.service;

import com.katalogus.project.entity.*;
import com.katalogus.project.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class DBInitializer {

    @Autowired
    TurnusRepository turnusRepository;

    @Autowired
    EloadasRepository eloadasRepository;

    @Autowired
    GyakorlatRepository gyakorlatRepository;

    @Autowired
    KonzultacioRepository konzultacioRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @PostConstruct
    public void postConstruct() {
        turnusRepository.save(Turnus.builder()
                .id((long) 1)
                .name("Turnus_1")
                .consultation(100)
                .practice(100)
                .lecture(75)
                .build());
        turnusRepository.save(Turnus.builder()
                .id((long) 2)
                .name("Turnus_2")
                .consultation(100)
                .practice(100)
                .lecture(75)
                .build());
        teacherRepository.save(Teacher.builder()
                .email("teacher1@teacher.com")
                .name("tanar1")
                .password("password")
                .build());
        teacherRepository.save(Teacher.builder()
                .email("teacher2@teacher.com")
                .name("tanar2")
                .password("password")
                .build());

        Eloadas eloadas1t1 = Eloadas.builder()
                .active(false)
                .code(null)
                .date(new Date())
                .isAttendanceOpen(false)
                .name("eloadas1t1")
                .point(1)
                .potlas(false)
                .turnus_id((long) 1)
                .id((long) 11)
                .build();
        Eloadas eloadas2t1 = Eloadas.builder()
                .active(false)
                .code(null)
                .date(new Date())
                .isAttendanceOpen(false)
                .name("eloadas2t1")
                .point(1)
                .potlas(false)
                .turnus_id((long) 1)
                .id((long) 12)
                .build();
        Eloadas eloadas3t1 = Eloadas.builder()
                .active(false)
                .code(null)
                .date(new Date())
                .isAttendanceOpen(false)
                .name("eloadas3t1")
                .point(1)
                .potlas(true)
                .turnus_id((long) 1)
                .id((long) 13)
                .build();
        Eloadas eloadas1t2 = Eloadas.builder()
                .active(false)
                .code(null)
                .date(new Date())
                .isAttendanceOpen(false)
                .name("eloadas1t2")
                .point(1)
                .potlas(false)
                .turnus_id((long) 2)
                .id((long) 14)
                .build();
        Eloadas eloadas2t2 = Eloadas.builder()
                .active(false)
                .code(null)
                .date(new Date())
                .isAttendanceOpen(false)
                .name("eloadas2t2")
                .point(1)
                .potlas(false)
                .turnus_id((long) 2)
                .id((long) 15)
                .build();
        Eloadas eloadas3t2 = Eloadas.builder()
                .active(false)
                .code(null)
                .date(new Date())
                .isAttendanceOpen(false)
                .name("eloadas3t2")
                .point(1)
                .potlas(true)
                .turnus_id((long) 2)
                .id((long) 16)
                .build();

        Konzultacio konzultacio1t1 = Konzultacio.builder()
                .active(false)
                .code(null)
                .date(new Date())
                .isAttendanceOpen(false)
                .name("konzultacio1t1")
                .point(1)
                .potlas(false)
                .turnus_id((long) 1)
                .id((long) 11)
                .build();
        Konzultacio konzultacio2t1 = Konzultacio.builder()
                .active(false)
                .code(null)
                .date(new Date())
                .isAttendanceOpen(false)
                .name("konzultacio2t1")
                .point(2)
                .potlas(false)
                .turnus_id((long) 1)
                .id((long) 12)
                .build();
        Konzultacio konzultacio3t1 = Konzultacio.builder()
                .active(false)
                .code(null)
                .date(new Date())
                .isAttendanceOpen(false)
                .name("konzultacio3t1")
                .point(2)
                .potlas(false)
                .turnus_id((long) 1)
                .id((long) 13)
                .build();
        Konzultacio konzultacio1t2 = Konzultacio.builder()
                .active(false)
                .code(null)
                .date(new Date())
                .isAttendanceOpen(false)
                .name("konzultacio1t2")
                .point(1)
                .potlas(false)
                .turnus_id((long) 2)
                .id((long) 14)
                .build();
        Konzultacio konzultacio2t2 = Konzultacio.builder()
                .active(false)
                .code(null)
                .date(new Date())
                .isAttendanceOpen(false)
                .name("konzultacio2t2")
                .point(2)
                .potlas(false)
                .turnus_id((long) 2)
                .id((long) 15)
                .build();
        Konzultacio konzultacio3t2 = Konzultacio.builder()
                .active(false)
                .code(null)
                .date(new Date())
                .isAttendanceOpen(false)
                .name("konzultacio3t2")
                .point(2)
                .potlas(false)
                .turnus_id((long) 2)
                .id((long) 16)
                .build();
        Gyakorlat gyakorlat1t1 = Gyakorlat.builder()
                .active(false)
                .code(null)
                .date(new Date())
                .isAttendanceOpen(false)
                .name("gyakorlat1t1")
                .point(1)
                .potlas(false)
                .turnus_id((long) 1)
                .id((long) 11)
                .build();
        Gyakorlat gyakorlat2t1 = Gyakorlat.builder()
                .active(false)
                .code(null)
                .date(new Date())
                .isAttendanceOpen(false)
                .name("gyakorlat2t1")
                .point(1)
                .potlas(false)
                .turnus_id((long) 1)
                .id((long) 12)
                .build();
        Gyakorlat gyakorlat3t1 = Gyakorlat.builder()
                .active(false)
                .code(null)
                .date(new Date())
                .isAttendanceOpen(false)
                .name("gyakorlat3t1")
                .point(1)
                .potlas(false)
                .turnus_id((long) 1)
                .id((long) 13)
                .build();
        Gyakorlat gyakorlat1t2 = Gyakorlat.builder()
                .active(false)
                .code(null)
                .date(new Date())
                .isAttendanceOpen(false)
                .name("gyakorlat1t2")
                .point(1)
                .potlas(false)
                .turnus_id((long) 2)
                .id((long) 14)
                .build();
        Gyakorlat gyakorlat2t2 = Gyakorlat.builder()
                .active(false)
                .code(null)
                .date(new Date())
                .isAttendanceOpen(false)
                .name("gyakorlat2t2")
                .point(1)
                .potlas(false)
                .turnus_id((long) 2)
                .id((long) 15)
                .build();
        Gyakorlat gyakorlat3t2 = Gyakorlat.builder()
                .active(false)
                .code(null)
                .date(new Date())
                .isAttendanceOpen(false)
                .name("gyakorlat3t2")
                .point(1)
                .potlas(false)
                .turnus_id((long) 2)
                .id((long) 16)
                .build();

        eloadasRepository.save(eloadas1t1);
        eloadasRepository.save(eloadas2t1);
        eloadasRepository.save(eloadas3t1);

        eloadasRepository.save(eloadas1t2);
        eloadasRepository.save(eloadas2t2);
        eloadasRepository.save(eloadas3t2);


        konzultacioRepository.save(konzultacio1t1);
        konzultacioRepository.save(konzultacio2t1);
        konzultacioRepository.save(konzultacio3t1);

        konzultacioRepository.save(konzultacio1t2);
        konzultacioRepository.save(konzultacio2t2);
        konzultacioRepository.save(konzultacio3t2);

        gyakorlatRepository.save(gyakorlat1t1);
        gyakorlatRepository.save(gyakorlat2t1);
        gyakorlatRepository.save(gyakorlat3t1);

        gyakorlatRepository.save(gyakorlat1t2);
        gyakorlatRepository.save(gyakorlat2t2);
        gyakorlatRepository.save(gyakorlat3t2);

        List<Gyakorlat> gyakorlatListt1 = gyakorlatRepository.findAllByTurnus_id((long) 1);
        List<Konzultacio> konzultacioListt1 = konzultacioRepository.findAllByTurnus_id((long) 1);
        List<Eloadas> eloadasListt1 = eloadasRepository.findAllByTurnus_id((long) 1);

        Student student1t1 = Student.builder()
                .email("student1t1@student.com")
                .name("Student1t1")
                .neptunCode("QWERTZ")
                .password("password")
                .turnus_id((long) 1)
                .gyakorlatList(gyakorlatListt1.subList(0, 3))
                .eloadasList(eloadasListt1.subList(0, 2))
                .konzultacioList(konzultacioListt1.subList(0, 1))
                .build();
        Student student2t1 = Student.builder()
                .email("student2t1@student.com")
                .name("Student2t1")
                .neptunCode("QWERTZ")
                .password("password")
                .turnus_id((long) 1)
                .gyakorlatList(gyakorlatListt1.subList(0, 1))
                .eloadasList(eloadasListt1.subList(0, 3))
                .konzultacioList(konzultacioListt1.subList(0, 2))
                .build();
        Student student3t1 = Student.builder()
                .email("student3t1@student.com")
                .name("Student3t1")
                .neptunCode("QWERTZ")
                .password("password")
                .turnus_id((long) 1)
                .gyakorlatList(gyakorlatListt1.subList(0, 2))
                .eloadasList(eloadasListt1.subList(0, 1))
                .konzultacioList(konzultacioListt1.subList(0, 3))
                .build();
        Student student4t1 = Student.builder()
                .email("student4t1@student.com")
                .name("Student4t1")
                .neptunCode("QWERTZ")
                .password("password")
                .turnus_id((long) 1)
                .gyakorlatList(gyakorlatListt1)
                .eloadasList(eloadasListt1)
                .konzultacioList(konzultacioListt1)
                .build();
        Student student1t2 = Student.builder()
                .email("student1t2@student.com")
                .name("Student1t2")
                .neptunCode("QWERTZ")
                .password("password")
                .turnus_id((long) 2)
                .build();
        Student student2t2 = Student.builder()
                .email("student2t2@student.com")
                .name("Student2t2")
                .neptunCode("QWERTZ")
                .password("password")
                .turnus_id((long) 2)
                .build();
        Student student3t2 = Student.builder()
                .email("student3t2@student.com")
                .name("Student3t2")
                .neptunCode("QWERTZ")
                .password("password")
                .turnus_id((long) 2)
                .build();
        Student student4t2 = Student.builder()
                .email("student4t2@student.com")
                .name("Student4t2")
                .neptunCode("QWERTZ")
                .password("password")
                .turnus_id((long) 2)
                .build();


        studentRepository.save(student1t1);
        studentRepository.save(student2t1);
        studentRepository.save(student3t1);
        studentRepository.save(student4t1);

        studentRepository.save(student1t2);
        studentRepository.save(student2t2);
        studentRepository.save(student3t2);
        studentRepository.save(student4t2);


    }
}
