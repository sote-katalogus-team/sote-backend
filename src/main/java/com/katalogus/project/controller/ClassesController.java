package com.katalogus.project.controller;

import com.katalogus.project.model.Classes;
import com.katalogus.project.model.StudentStatistic;
import com.katalogus.project.service.ClassesProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/classes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ClassesController {

    @Autowired
    ClassesProvider classesProvider;

    @GetMapping("/all/{turnus_id}")
    public Classes getAllClasses(@PathVariable("turnus_id") Long turnusId) {
        return classesProvider.getAllByTurnusId(turnusId);
    }

    @GetMapping("/statistic/{turnus_id}")
    public List<StudentStatistic> getAllStatistic(@PathVariable("turnus_id") Long turnusId) {
        return classesProvider.getAllStatistic(turnusId);
    }

    @GetMapping("/find_by_date")
    public Classes getClassesByDate(@RequestBody Date date) {
        return  classesProvider.getClassesByDate(date);
    }
}