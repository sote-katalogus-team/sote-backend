package com.katalogus.project.controller;

import com.katalogus.project.entity.Teacher;
import com.katalogus.project.service.TeacherProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    TeacherProvider teacherProvider;

    @GetMapping("/all")
    public List<Teacher> getAllTeacher() {
        return teacherProvider.getAll();
    }

    @PostMapping("/add")
    public ResponseEntity saveNewTeacher(@RequestBody Teacher teacher) {
        Boolean successful = teacherProvider.saveNewTeacher(teacher);
        if (successful) {
            return ResponseEntity.ok("New Teacher created successfully");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{teacher_id}/update")
    public ResponseEntity updateTeacherById(@RequestBody Teacher teacher, @PathVariable("teacher_id") Long teacherId) {
        Boolean successful = teacherProvider.updateTeacherById(teacher, teacherId);
        if (successful) {
            return ResponseEntity.ok("Teacher updated successfully");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{teacher_id}/delete")
    public void deleteTeacherById(@PathVariable("teacher_id") Long teacherId) {
        teacherProvider.deleteTeacherById(teacherId);
    }
}
