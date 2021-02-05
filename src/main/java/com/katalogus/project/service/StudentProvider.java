package com.katalogus.project.service;

import com.katalogus.project.entity.Student;
import com.katalogus.project.model.Classes;
import com.katalogus.project.model.StudentStatistic;
import com.katalogus.project.repository.StudentRepository;
import com.katalogus.project.utility.AttendancePercentage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentProvider {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    AttendancePercentage attendancePercentage;

    @Autowired
    ClassesProvider classesProvider;


    public List<Student> getAll() {
        return studentRepository.findAll();

    }

    public Boolean saveNewStudent(Student student) {
        Object response = studentRepository.save(student);
        return response.getClass().equals(Student.class);

    }

    public Boolean updateStudentById(Student student, Long studentId) {
        student.setId(studentId);
        Object response = studentRepository.save(student);
        return response.getClass().equals(Student.class);
    }

    public Boolean deleteStudentById(Long studentId) {
        long before = studentRepository.count();
        studentRepository.deleteById(studentId);
        long after = studentRepository.count();
        return before > after;
    }

    public StudentStatistic getStudentStatisticByStudentId(Long studentId) {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            Classes classes = classesProvider.getAllByTurnusId(student.getTurnus_id());
            return StudentStatistic.builder()
                    .percentages(attendancePercentage.calculateAttendancePercentages(student, classes))
                    .neptunCode(student.getNeptun_code())
                    .studentName(student.getName())
                    .build();
        }
        return null;
    }
}