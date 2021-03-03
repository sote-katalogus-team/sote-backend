package com.katalogus.project.controller;

import com.katalogus.project.entity.Student;
import com.katalogus.project.model.ClassInfo;
import com.katalogus.project.model.ClassType;
import com.katalogus.project.model.ManualAttendance;
import com.katalogus.project.model.StudentStatistic;
import com.katalogus.project.service.StudentProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class StudentController {

    @Autowired
    StudentProvider studentProvider;

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/all")
    public List<Student> getAllStudent() {
        return studentProvider.getAll();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/find_by_turnus_id/{turnus_id}")
    public List<Student> getAllStudentByTurnusId(@PathVariable("turnus_id") Long turnusId) {
        return studentProvider.getAllStudentByTurnusId(turnusId);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<String> saveNewStudent(@RequestBody Student student) {
        HashMap<Boolean, String> successful = studentProvider.saveNewStudent(student);
        if (successful.containsKey(true)) {
            return ResponseEntity.ok(successful.get(true));
        } else {
            return ResponseEntity.badRequest().body(successful.get(false));
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/head_count/{turnus_id}")
    public ResponseEntity<Integer> getHeadCount(@PathVariable("turnus_id") Long turnusId) {
        Integer headCount = studentProvider.getHeadCount(turnusId);
        if (headCount == -1) {
            return ResponseEntity.badRequest().body(-1);
        } else {
            return ResponseEntity.ok(headCount);
        }
    }

    @PutMapping("/{student_id}/name/update")
    public ResponseEntity<String> updateStudentsNameById(@RequestBody HashMap<String, String> nameMap, @PathVariable("student_id") Long studentId) {
        Boolean successful = false;
        if (nameMap.get("name") != null) {
            String name = nameMap.get("name");
            successful = studentProvider.updateStudentsNameById(name, studentId);

        }

        if (successful) {
            return ResponseEntity.ok("Student updated successfully");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{student_id}/email/update")
    public ResponseEntity<String> updateStudentsEmailById(@RequestBody HashMap<String, String> emailMap, @PathVariable("student_id") Long studentId) {
        Boolean successful = false;

        if (emailMap.get("email") != null) {
            successful = studentProvider.updateStudentsEmailById(emailMap.get("email"), studentId);
        }
        if (successful) {
            return ResponseEntity.ok("Student updated successfully");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{student_id}/turnus/update")
    public ResponseEntity<String> updateStudentsTurnusById(@RequestBody HashMap<String, String> turnusMap, @PathVariable("student_id") Long studentId) {
        Boolean successful = false;
        if (turnusMap.get("turnusId") != null) {
            successful = studentProvider.updateStudentsTurnusById(Long.parseLong(turnusMap.get("turnusId")), studentId);
        }
        if (successful) {
            return ResponseEntity.ok("Student updated successfully");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
    @PutMapping("/{student_id}/password/update")
    public ResponseEntity<String> updateStudentsPasswordById(@RequestBody HashMap<String, String> passwordMap, @PathVariable("student_id") Long studentId) {
        Boolean successful = false;
        if (passwordMap.get("password") != null) {
            successful = studentProvider.updateStudentsPasswordById(passwordMap.get("password"), studentId);
        }
        if (successful) {
            return ResponseEntity.ok("Student updated successfully");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{student_id}/neptunCode/update")
    public ResponseEntity<String> updateStudentsNeptunCodeById(@RequestBody HashMap<String, String> neptunCodeMap, @PathVariable("student_id") Long studentId) {
        Boolean successful = false;
        if (neptunCodeMap.get("neptunCode") != null) {
            successful = studentProvider.updateStudentsNeptunCodeById(neptunCodeMap.get("neptunCode"), studentId);
        }

        if (successful) {
            return ResponseEntity.ok("Student updated successfully");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/{student_id}/delete")
    public ResponseEntity<String> deleteStudentById(@PathVariable("student_id") Long studentId) {
        Boolean successful = studentProvider.deleteStudentById(studentId);
        if (successful) {
            return ResponseEntity.ok("Student deletion successfully");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PreAuthorize("hasAnyAuthority('STUDENT')")
    @GetMapping("/{student_id}/statistics")
    public StudentStatistic getStudentStatisticByStudentId(@PathVariable("student_id") Long studentId) {
        return studentProvider.getStudentStatisticByStudentId(studentId);
    }

    @PreAuthorize("hasAnyAuthority('STUDENT')")
    @PostMapping("/{student_id}/send_code")
    public ResponseEntity<String> sendInCode(@PathVariable("student_id") Long studentId, @RequestBody HashMap<String, String> code) {
        HashMap<Boolean, String> success = studentProvider.sendInCode(studentId, code);
        if (success.containsKey(true)) {
            return ResponseEntity.ok(success.get(true));
        } else {
            return ResponseEntity.badRequest().body(success.get(false));
        }
    }

    @PreAuthorize("hasAnyAuthority('TEACHER','ADMIN')")
    @PostMapping("/addByNeptunCode")
    public ResponseEntity<String> addByNeptunCode(@RequestBody ManualAttendance manualAttendance) {
        HashMap<Boolean, String> success = studentProvider.addByNeptunCode(manualAttendance);
        if (success.containsKey(true)) {
            return ResponseEntity.ok(success.get(true));
        } else {
            return ResponseEntity.badRequest().body(success.get(false));
        }
    }

    @PreAuthorize("hasAnyAuthority('TEACHER','ADMIN')")
    @GetMapping("/countStudent")
    public Integer countStudentAtClass(@PathParam("id") Long id, @PathParam("type") ClassType type) {
        ClassInfo classInfo = new ClassInfo(type, id);
        return studentProvider.countStudentAtClass(classInfo);
    }


}