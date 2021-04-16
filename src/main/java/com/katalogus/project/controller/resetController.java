package com.katalogus.project.controller;


import com.katalogus.project.entity.Teacher;
import com.katalogus.project.repository.StudentRepository;
import com.katalogus.project.repository.TeacherRepository;
import com.katalogus.project.security.ApplicationUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class resetController {

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private StudentRepository studentRepository;


    @GetMapping("/reset/add-base-data")
    public String notAGoodThing() {
        List<Teacher> all = teacherRepository.findAll();

        all.forEach(teacher -> {
            teacherRepository.deleteById(teacher.getId());
        });


        if (teacherRepository.count() == 0) {
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

        return "success";
    }

    @GetMapping("/remove/maryn")
    public String removeMAryn() {
        studentRepository.deleteById(1L);
        return "success";
    }

}
