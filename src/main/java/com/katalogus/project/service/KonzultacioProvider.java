package com.katalogus.project.service;

import com.katalogus.project.entity.Konzultacio;
import com.katalogus.project.repository.KonzultacioRepository;
import com.katalogus.project.utility.RandomCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class KonzultacioProvider {

    @Autowired
    KonzultacioRepository konzultacioRepository;

    @Autowired
    RandomCodeGenerator randomCodeGenerator;

    public Boolean saveNewKonzultacio(Konzultacio konzultacio) {
        Object response = konzultacioRepository.save(konzultacio);
        return response.getClass().equals(Konzultacio.class);
    }

    public Boolean updateKonzultacioById(Konzultacio konzultacio, Long konzultacioId) {
        konzultacio.setId(konzultacioId);
        Object response = konzultacioRepository.save(konzultacio);
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
}
