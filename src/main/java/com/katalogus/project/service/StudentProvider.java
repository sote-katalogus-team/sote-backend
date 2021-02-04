package com.katalogus.project.service;

import com.katalogus.project.entity.Student;
import com.katalogus.project.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentProvider {

    @Autowired
    StudentRepository studentRepository;

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

    public Boolean deleteStudentById(Long studentId){
        long before = studentRepository.count();
        studentRepository.deleteById(studentId);
        long after = studentRepository.count();
        return before > after;
    }

}