package com.katalogus.project.service;

import com.katalogus.project.entity.Konzultacio;
import com.katalogus.project.entity.Student;
import com.katalogus.project.repository.KonzultacioRepository;
import com.katalogus.project.repository.StudentRepository;
import com.katalogus.project.utility.RandomCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class KonzultacioProvider {

    @Autowired
    KonzultacioRepository konzultacioRepository;
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    RandomCodeGenerator randomCodeGenerator;

    public Boolean saveNewKonzultacio(Konzultacio konzultacio) {
        Object response = konzultacioRepository.save(konzultacio);
        return response.getClass().equals(Konzultacio.class);
    }

    public Boolean updateKonzultacioById(Konzultacio konzultacio, Long konzultacioId) {
        Optional<Konzultacio> optionalKonzultacio = konzultacioRepository.findById(konzultacioId);
        Konzultacio oldKonzultacio = optionalKonzultacio.get();
        oldKonzultacio.setName(konzultacio.getName());
        oldKonzultacio.setDate(konzultacio.getDate());
        oldKonzultacio.setPotlas(konzultacio.getPotlas());
        oldKonzultacio.setActive(konzultacio.getActive());
        oldKonzultacio.setAttendanceType(konzultacio.getAttendanceType());
        Object response = konzultacioRepository.save(oldKonzultacio);
        return response.getClass().equals(Konzultacio.class);
    }

    public Boolean deleteKonzultacioById(Long konzultacioId) {
        long before = konzultacioRepository.count();
        konzultacioRepository.deleteById(konzultacioId);
        long after = konzultacioRepository.count();
        return before > after;
    }

    public Boolean changeActiveStatusById(Long konzultacioId) {
        boolean success = false;
        Optional<Konzultacio> optionalKonzultacio = konzultacioRepository.findById(konzultacioId);
        if (optionalKonzultacio.isPresent()) {
            Konzultacio konzultacio = optionalKonzultacio.get();
            konzultacio.setActive(!konzultacio.getActive());
            konzultacioRepository.save(konzultacio);
            success = true;
        }
        return success;
    }

    public Konzultacio getKonzultacioById(Long konzultacioId) {
        return konzultacioRepository.findById(konzultacioId).get();
    }


    public String openClassForAttendace(Long konzultacioId) {
        String code = "There is no konzultacio with this Id";
        Optional<Konzultacio> optionalKonzultacio = konzultacioRepository.findById(konzultacioId);
        if (optionalKonzultacio.isPresent()) {
            Konzultacio konzultacio = optionalKonzultacio.get();
            code = randomCodeGenerator.codeGenerator();
            konzultacio.setCode(code);
            konzultacio.setIsAttendanceOpen(true);
            konzultacioRepository.save(konzultacio);
        }
        return code;
    }

    public Boolean closeClassForAttendace(Long konzultacioId) {
        boolean success = false;
        Optional<Konzultacio> optionalKonzultacio = konzultacioRepository.findById(konzultacioId);
        if (optionalKonzultacio.isPresent()) {
            Konzultacio konzultacio = optionalKonzultacio.get();
            konzultacio.setIsAttendanceOpen(false);
            konzultacioRepository.save(konzultacio);
            success = true;
        }
        return success;
    }

    public List<Student> getStudentsById(Long id) {
        List<Student> result = new ArrayList<>();
        List<Student> studentList = studentRepository.findAll();
        for (Student currentStudent : studentList) {
            for (Konzultacio konzultacio : currentStudent.getKonzultacioList()) {
                if (konzultacio.getId().equals(id)) {
                    result.add(currentStudent);
                }
            }
        }
        return result;
    }
}
