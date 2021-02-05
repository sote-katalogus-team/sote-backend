package com.katalogus.project.controller;

import com.katalogus.project.entity.Student;
import com.katalogus.project.model.StudentStatistic;
import com.katalogus.project.service.StudentProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
    public ResponseEntity<String> saveNewStudent(@RequestBody Student student) {
        Boolean successful = studentProvider.saveNewStudent(student);
        if (successful) {
            return ResponseEntity.ok("New Student created successfully");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


    @PutMapping("/{student_id}/update")
    public ResponseEntity<String> updateStudentById(@RequestBody Student student, @PathVariable("student_id") Long studentId) {
        Boolean successful = studentProvider.updateStudentById(student, studentId);
        if (successful) {
            return ResponseEntity.ok("Student updated successfully");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{student_id}/delete")
    public ResponseEntity<String> deleteStudentById(@PathVariable("student_id") Long studentId) {
        Boolean successful = studentProvider.deleteStudentById(studentId);
        if (successful) {
            return ResponseEntity.ok("Student deletion successfully");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{student_id}/statistics")
    public StudentStatistic getStudentStatisticByStudentId(@PathVariable("student_id") Long studentId) {
        return studentProvider.getStudentStatisticByStudentId(studentId);
    }

    @PostMapping("/{student_id}/send_code")
    public ResponseEntity<String> sendInCode(@PathVariable("student_id") Long studentId, @RequestBody HashMap<String, String> code) {
        HashMap<Boolean, String> success = studentProvider.sendInCode(studentId, code);
        if (success.containsKey(true)) {
            return ResponseEntity.ok(success.get(true));
        } else {
            return ResponseEntity.badRequest().body(success.get(false));
        }
    }
}