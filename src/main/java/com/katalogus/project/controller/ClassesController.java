package com.katalogus.project.controller;

import com.katalogus.project.entity.Turnus;
import com.katalogus.project.model.Classes;
import com.katalogus.project.service.ClassesProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/classes")
public class ClassesController {

    @Autowired
    ClassesProvider classesProvider;

    @GetMapping("/all/{turnus_id}")
    public Classes getAllClasses(@PathVariable("turnus_id") Long turnusId) {
        return classesProvider.getAllByTurnusId(turnusId);
    }
}