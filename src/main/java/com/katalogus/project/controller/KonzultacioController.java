package com.katalogus.project.controller;

import com.katalogus.project.entity.Konzultacio;
import com.katalogus.project.service.KonzultacioProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/konzultacio")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class KonzultacioController {

    @Autowired
    KonzultacioProvider konzultacioProvider;

    @PostMapping("/add")
    public ResponseEntity<String> saveNewKonzultacio(@RequestBody Konzultacio konzultacio) {
        Boolean successful = konzultacioProvider.saveNewKonzultacio(konzultacio);
        if (successful) {
            return ResponseEntity.ok("New Konzultacio created successfully");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{konzultacio_id}/update")
    public ResponseEntity<String> updateKonzultacioById(@RequestBody Konzultacio konzultacio, @PathVariable("konzultacio_id") Long konzultacioId) {
        Boolean successful = konzultacioProvider.updateKonzultacioById(konzultacio, konzultacioId);
        if (successful) {
            return ResponseEntity.ok("Konzultacio updated successfully");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{konzultacio_id}/delete")
    public ResponseEntity<String> deleteKonzultacioById(@PathVariable("konzultacio_id") Long konzultacioId) {
        Boolean successful = konzultacioProvider.deleteKonzultacioById(konzultacioId);
        if (successful) {
            return ResponseEntity.ok("Konzultacio deletion successfully");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{konzultacio_id}/change_status")
    public ResponseEntity<String> changeActiveStatusById(@PathVariable("konzultacio_id") Long konzultacioId) {
        Boolean successful = konzultacioProvider.changeActiveStatusById(konzultacioId);
        if (successful) {
            return ResponseEntity.ok("Konzultacio's status changed successfully");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
