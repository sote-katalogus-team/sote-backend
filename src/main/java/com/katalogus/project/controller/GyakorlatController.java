package com.katalogus.project.controller;

import com.katalogus.project.entity.Eloadas;
import com.katalogus.project.entity.Gyakorlat;
import com.katalogus.project.entity.Student;
import com.katalogus.project.service.ClassesProvider;
import com.katalogus.project.service.GyakorlatProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gyakorlat")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GyakorlatController {

    @Autowired
    GyakorlatProvider gyakorlatProvider;

    @PreAuthorize("hasAnyAuthority('TEACHER','ADMIN')")
    @GetMapping("/{gyakorlat_id}")
    public Gyakorlat getGyakorlatById(@PathVariable("gyakorlat_id") Long gyakorlatId) {
        return gyakorlatProvider.getGyakorlatById(gyakorlatId);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<String> saveNewGyakorlat(@RequestBody Gyakorlat gyakorlat) {
        Boolean successful = gyakorlatProvider.saveNewGyakorlat(gyakorlat);
        if (successful) {
            return ResponseEntity.ok("New Gyakorlat created successfully");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PutMapping("/{gyakorlat_id}/update")
    public ResponseEntity<String> updateGyakorlatById(@RequestBody Gyakorlat gyakorlat, @PathVariable("gyakorlat_id") Long gyakorlatId) {
        Boolean successful = gyakorlatProvider.updateGyakorlatById(gyakorlat, gyakorlatId);
        if (successful) {
            return ResponseEntity.ok("Gyakorlat updated successfully");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/{gyakorlat_id}/delete")
    public ResponseEntity<String> deleteGyakorlatById(@PathVariable("gyakorlat_id") Long gyakorlatId) {
        Boolean successful = gyakorlatProvider.deleteGyakorlatById(gyakorlatId);
        if (successful) {
            return ResponseEntity.ok("Gyakorlat deletion successfully");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PreAuthorize("hasAnyAuthority('TEACHER','ADMIN')")
    @PutMapping("/{gyakorlat_id}/change_status")
    public ResponseEntity<String> changeActiveStatusById(@PathVariable("gyakorlat_id") Long gyakorlatId) {
        Boolean successful = gyakorlatProvider.changeActiveStatusById(gyakorlatId);
        if (successful) {
            return ResponseEntity.ok("Gyakorlat's status changed successfully");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PreAuthorize("hasAnyAuthority('TEACHER','ADMIN')")
    @GetMapping("/{gyakorlat_id}/openClass")
    public String openClassForAttendance(@PathVariable("gyakorlat_id") Long gyakorlatId) {
        return gyakorlatProvider.openClassForAttendace(gyakorlatId);
    }

    @PreAuthorize("hasAnyAuthority('TEACHER','ADMIN')")
    @PostMapping("/{gyakorlat_id}/closeClass")
    public ResponseEntity<String> closeClassForAttendance(@PathVariable("gyakorlat_id") Long gyakorlatId) {
        Boolean successful = gyakorlatProvider.closeClassForAttendace(gyakorlatId);
        if (successful) {
            return ResponseEntity.ok("Gyakorlat closed successfully");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/{gyakorlat_id}/students")
    public List<Student> getStudentByGyakorlatId(@PathVariable Long gyakorlat_id) {
        System.out.println(gyakorlat_id);
        return gyakorlatProvider.getStudentsById(gyakorlat_id);
    }
}
