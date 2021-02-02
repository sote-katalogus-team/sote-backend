package com.katalogus.project.controller;

import com.katalogus.project.entity.Konzultacio;
import com.katalogus.project.service.ClassesProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/konzultacio")
public class KonzultacioController {

    @Autowired
    ClassesProvider classesProvider;

    @PostMapping("/add")
    public ResponseEntity saveNewKonzultacio(@RequestBody Konzultacio konzultacio) {
        Boolean successful = classesProvider.saveNewKonzultacio(konzultacio);
        if (successful) {
            return ResponseEntity.ok("New Konzultacio created successfully");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{konzultacio_id}/update")
    public ResponseEntity updateKonzultacioById(@RequestBody Konzultacio konzultacio, @PathVariable("konzultacio_id") Long konzultacioId) {
        Boolean successful = classesProvider.updateKonzultacioById(konzultacio, konzultacioId);
        if (successful) {
            return ResponseEntity.ok("Konzultacio updated successfully");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{konzultacio_id}/delete")
    public void deleteKonzultacioById(@PathVariable("konzultacio_id") Long konzultacioId) {
        classesProvider.deleteKonzultacioById(konzultacioId);
    }
}
