package com.katalogus.project.controller;

import com.katalogus.project.entity.Teacher;
import com.katalogus.project.service.TeacherProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TeacherController {

    @Autowired
    TeacherProvider teacherProvider;

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/all")
    public List<Teacher> getAllTeacher() {
        return teacherProvider.getAll();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity saveNewTeacher(@RequestBody Teacher teacher) {
        Boolean successful = teacherProvider.saveNewTeacher(teacher);
        if (successful) {
            return ResponseEntity.ok("New Teacher created successfully");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PutMapping("/{teacher_id}/update")
    public ResponseEntity updateTeacherById(@RequestBody Teacher teacher, @PathVariable("teacher_id") Long teacherId) {
        Boolean successful = teacherProvider.updateTeacherById(teacher, teacherId);
        if (successful) {
            return ResponseEntity.ok("Teacher updated successfully");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/{teacher_id}/delete")
    public ResponseEntity<String> deleteTeacherById(@PathVariable("teacher_id") Long teacherId) {
        Boolean successful = teacherProvider.deleteTeacherById(teacherId);
        if (successful) {
            return ResponseEntity.ok("Teacher deletion successfully");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
