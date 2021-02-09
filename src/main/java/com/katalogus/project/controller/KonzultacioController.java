package com.katalogus.project.controller;

import com.katalogus.project.entity.Konzultacio;
import com.katalogus.project.service.KonzultacioProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/konzultacio")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class KonzultacioController {

    @Autowired
    KonzultacioProvider konzultacioProvider;

    @PreAuthorize("hasAnyAuthority('TEACHER','ADMIN')")
    @GetMapping("/{konzultacio_id}")
    public Konzultacio getKonzultacioById(@PathVariable("konzultacio_id") Long konzultacioId) {
        return konzultacioProvider.getKonzultacioById(konzultacioId);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<String> saveNewKonzultacio(@RequestBody Konzultacio konzultacio) {
        Boolean successful = konzultacioProvider.saveNewKonzultacio(konzultacio);
        if (successful) {
            return ResponseEntity.ok("New Konzultacio created successfully");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PutMapping("/{konzultacio_id}/update")
    public ResponseEntity<String> updateKonzultacioById(@RequestBody Konzultacio konzultacio, @PathVariable("konzultacio_id") Long konzultacioId) {
        Boolean successful = konzultacioProvider.updateKonzultacioById(konzultacio, konzultacioId);
        if (successful) {
            return ResponseEntity.ok("Konzultacio updated successfully");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/{konzultacio_id}/delete")
    public ResponseEntity<String> deleteKonzultacioById(@PathVariable("konzultacio_id") Long konzultacioId) {
        Boolean successful = konzultacioProvider.deleteKonzultacioById(konzultacioId);
        if (successful) {
            return ResponseEntity.ok("Konzultacio deletion successfully");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PreAuthorize("hasAnyAuthority('TEACHER','ADMIN')")
    @PutMapping("/{konzultacio_id}/change_status")
    public ResponseEntity<String> changeActiveStatusById(@PathVariable("konzultacio_id") Long konzultacioId) {
        Boolean successful = konzultacioProvider.changeActiveStatusById(konzultacioId);
        if (successful) {
            return ResponseEntity.ok("Konzultacio's status changed successfully");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PreAuthorize("hasAnyAuthority('TEACHER','ADMIN')")
    @GetMapping("/{konzultacio_id}/openClass")
    public String openClassForAttendance(@PathVariable("konzultacio_id") Long konzultacioId) {
        return konzultacioProvider.openClassForAttendace(konzultacioId);
    }

    @PreAuthorize("hasAnyAuthority('TEACHER','ADMIN')")
    @PostMapping("/{konzultacio_id}/closeClass")
    public ResponseEntity<String> closeClassForAttendance(@PathVariable("konzultacio_id") Long konzultacioId) {
        Boolean successful = konzultacioProvider.closeClassForAttendace(konzultacioId);
        if (successful) {
            return ResponseEntity.ok("Konzultacio closed successfully");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
