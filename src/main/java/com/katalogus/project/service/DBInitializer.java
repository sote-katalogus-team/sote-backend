package com.katalogus.project.service;

import com.katalogus.project.entity.*;
import com.katalogus.project.repository.*;
import com.katalogus.project.security.ApplicationUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
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
        teacherRepository.save(Teacher.builder()
                .email("tanar1@sote.hu")
                .name("tanar1")
                .password(passwordEncoder.encode("sote123"))
                .roles(List.of(ApplicationUserRole.TEACHER))
                .build());
        teacherRepository.save(Teacher.builder()
                .email("tanar2@sote.hu")
                .name("tanar2")
                .password(passwordEncoder.encode("sote123"))
                .roles(List.of(ApplicationUserRole.TEACHER))
                .build());
        teacherRepository.save(Teacher.builder()
                .email("katalogus@gyermekklinika.com")
                .name("admin")
                .password(passwordEncoder.encode("sote123"))
                .roles(List.of(ApplicationUserRole.TEACHER, ApplicationUserRole.ADMIN))
                .build());
    }
}
