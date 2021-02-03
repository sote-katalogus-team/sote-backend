package com.katalogus.project.controller;

import com.katalogus.project.entity.Student;
import com.katalogus.project.service.StudentProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class StudentController {

    @Autowired
    StudentProvider studentProvider;

    @GetMapping("/all")
    public List<Student> getAllStudent() {
        return studentProvider.getAll();
    }

    @PostMapping("/add")
    public ResponseEntity saveNewStudent(@RequestBody Student student) {
        Boolean successful = studentProvider.saveNewStudent(student);
        if (successful) {
            return ResponseEntity.ok("New Student created successfully");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


    @PutMapping("/{student_id}/update")
    public ResponseEntity updateStudentById(@RequestBody Student student, @PathVariable("student_id") Long studentId) {
        Boolean successful = studentProvider.updateStudentById(student, studentId);
        if (successful) {
            return ResponseEntity.ok("Student updated successfully");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{student_id}/delete")
    public void deleteStudentById(@PathVariable("student_id") Long studentId) {
        studentProvider.deleteStudentById(studentId);
    }
}