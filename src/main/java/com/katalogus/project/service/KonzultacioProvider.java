package com.katalogus.project.service;

import com.katalogus.project.entity.Gyakorlat;
import com.katalogus.project.entity.Konzultacio;
import com.katalogus.project.repository.KonzultacioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class KonzultacioProvider {

    @Autowired
    KonzultacioRepository konzultacioRepository;


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
}
