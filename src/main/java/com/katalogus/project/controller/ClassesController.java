package com.katalogus.project.controller;

import com.katalogus.project.model.ClassAttendance;
import com.katalogus.project.model.Classes;
import com.katalogus.project.model.StudentStatistic;
import com.katalogus.project.service.ClassesProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/classes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ClassesController {

    @Autowired
    ClassesProvider classesProvider;

    @PreAuthorize("hasAnyAuthority('TEACHER','ADMIN')")
    @GetMapping("/all/{turnus_id}")
    public Classes getAllClasses(@PathVariable("turnus_id") Long turnusId) {
        return classesProvider.getAllByTurnusId(turnusId);
    }

    @PreAuthorize("hasAnyAuthority('TEACHER','ADMIN')")
    @GetMapping("/student_statistic/{turnus_id}")
    public List<StudentStatistic> getAllStatistic(@PathVariable("turnus_id") Long turnusId) {
        return classesProvider.getAllStatistic(turnusId);
    }

    @PreAuthorize("hasAnyAuthority('TEACHER','ADMIN')")
    @GetMapping("/find_by_date")
    public Classes getClassesByDate() {
        return classesProvider.getClassesByDate(new Date());
    }

    @GetMapping("/statistic/{turnus_id}")
    public List<ClassAttendance> getClassesStatisByTurnusId(@PathVariable("turnus_id") Long turnusId) {
        return classesProvider.getClassesStatisByTurnusId(turnusId);
    }
}