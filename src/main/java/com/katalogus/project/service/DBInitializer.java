package com.katalogus.project.service;

import com.katalogus.project.entity.*;
import com.katalogus.project.repository.*;
import com.katalogus.project.security.ApplicationUserRole;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void postConstruct() {
        turnusRepository.save(Turnus.builder()
                .name("Turnus_angol")
                .consultation(100)
                .practice(100)
                .lecture(75)
                .year(2021)
                .combinedName("Turnus_angol/2021")
                .build());
        turnusRepository.save(Turnus.builder()
                .name("Turnus_magyar")
                .consultation(100)
                .practice(100)
                .lecture(75)
                .year(2021)
                .combinedName("Turnus_magyar/2021")
                .build());
        teacherRepository.save(Teacher.builder()
                .email("teacher1@teacher.com")
                .name("tanar1")
                .password(passwordEncoder.encode("password"))
                .roles(List.of(ApplicationUserRole.TEACHER))
                .build());
        teacherRepository.save(Teacher.builder()
                .email("teacher2@teacher.com")
                .name("tanar2")
                .password(passwordEncoder.encode("password"))
                .roles(List.of(ApplicationUserRole.TEACHER))
                .build());
        teacherRepository.save(Teacher.builder()
                .email("admin@admin.com")
                .name("admin")
                .password(passwordEncoder.encode("password"))
                .roles(List.of(ApplicationUserRole.TEACHER, ApplicationUserRole.ADMIN))
                .build());

        //Előadás generálás, és hozzáadás
        Eloadas eloadas1t1 = Eloadas.builder()
                .date(new Date())
                .name("eloadas1t1")
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 1)
                .code("qwertz")
                .active(false)
                .code(null)
                .build();
        eloadasRepository.save(eloadas1t1);

        //Konzultáció generálás, és hozzáadás
        Konzultacio konzultacio1t1 = Konzultacio.builder()
                .name("konzultacio1t1")
                .active(false)
                .code(null)
                .date(new Date())
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 1)
                .code("qwertz")
                .build();
        konzultacioRepository.save(konzultacio1t1);

        //Gyakorlat generálás, és hozzáadás
        Gyakorlat gyakorlat1t1 = Gyakorlat.builder()
                .name("gyakorlat1t1")
                .active(false)
                .code(null)
                .date(new Date())
                .isAttendanceOpen(false)
                .point(1)
                .potlas(false)
                .turnusId((long) 1)
                .code("qwertz")
                .build();
        gyakorlatRepository.save(gyakorlat1t1);

/*
        List<Gyakorlat> gyakorlatListt1 = gyakorlatRepository.findAllByTurnusId((long) 1);
        List<Konzultacio> konzultacioListt1 = konzultacioRepository.findAllByTurnusId((long) 1);
        List<Eloadas> eloadasListt1 = eloadasRepository.findAllByTurnusId((long) 1);

        Student student1t1 = Student.builder()
                .email("student1t1@student.com")
                .name("Student1t1")
                .neptunCode("QWERT1")
                .password(passwordEncoder.encode("password"))
                .turnusId((long) 1)
                .gyakorlatList(gyakorlatListt1.subList(0, 2))
                .eloadasList(eloadasListt1.subList(0, 1))
                .konzultacioList(konzultacioListt1.subList(0, 0))
                .roles(List.of(ApplicationUserRole.STUDENT))
                .build();
        Student student2t1 = Student.builder()
                .email("student2t1@student.com")
                .name("Student2t1")
                .neptunCode("QWERT2")
                .password(passwordEncoder.encode("password"))
                .turnusId((long) 1)
                .gyakorlatList(gyakorlatListt1.subList(0, 0))
                .eloadasList(eloadasListt1.subList(0, 2))
                .konzultacioList(konzultacioListt1.subList(0, 1))
                .roles(List.of(ApplicationUserRole.STUDENT))
                .build();
        Student student3t1 = Student.builder()
                .email("student3t1@student.com")
                .name("Student3t1")
                .neptunCode("QWERT3")
                .password(passwordEncoder.encode("password"))
                .turnusId((long) 1)
                .gyakorlatList(gyakorlatListt1.subList(0, 1))
                .eloadasList(eloadasListt1.subList(0, 0))
                .konzultacioList(konzultacioListt1.subList(0, 2))
                .roles(List.of(ApplicationUserRole.STUDENT))
                .build();
        Student student4t1 = Student.builder()
                .email("student4t1@student.com")
                .name("Student4t1")
                .neptunCode("QWERT4")
                .password(passwordEncoder.encode("password"))
                .turnusId((long) 1)
                .gyakorlatList(gyakorlatListt1)
                .eloadasList(eloadasListt1)
                .konzultacioList(konzultacioListt1)
                .roles(List.of(ApplicationUserRole.STUDENT))
                .build();
        Student student1t2 = Student.builder()
                .email("student1t2@student.com")
                .name("Student1t2")
                .neptunCode("QWERT5")
                .password(passwordEncoder.encode("password"))
                .turnusId((long) 2)
                .roles(List.of(ApplicationUserRole.STUDENT))
                .build();
        Student student2t2 = Student.builder()
                .email("student2t2@student.com")
                .name("Student2t2")
                .neptunCode("QWERT6")
                .password(passwordEncoder.encode("password"))
                .turnusId((long) 2)
                .roles(List.of(ApplicationUserRole.STUDENT))
                .build();
        Student student3t2 = Student.builder()
                .email("student3t2@student.com")
                .name("Student3t2")
                .neptunCode("QWERT7")
                .password(passwordEncoder.encode("password"))
                .turnusId((long) 2)
                .roles(List.of(ApplicationUserRole.STUDENT))
                .build();
        Student student4t2 = Student.builder()
                .email("student4t2@student.com")
                .name("Student4t2")
                .neptunCode("QWERT8")
                .password(passwordEncoder.encode("password"))
                .turnusId((long) 2)
                .roles(List.of(ApplicationUserRole.STUDENT))
                .build();


        studentRepository.save(student1t1);
        studentRepository.save(student2t1);
        studentRepository.save(student3t1);
        studentRepository.save(student4t1);

        studentRepository.save(student1t2);
        studentRepository.save(student2t2);
        studentRepository.save(student3t2);
        studentRepository.save(student4t2);

 */


    }
}
