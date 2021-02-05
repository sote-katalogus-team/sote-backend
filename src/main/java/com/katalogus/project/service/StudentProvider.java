package com.katalogus.project.service;

import com.katalogus.project.entity.*;
import com.katalogus.project.model.Classes;
import com.katalogus.project.model.StudentStatistic;
import com.katalogus.project.repository.StudentRepository;
import com.katalogus.project.utility.AttendancePercentage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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

    public HashMap<Boolean, String> sendInCode(Long studentId, HashMap<String, String> code) {
        HashMap<Boolean, String> success = new HashMap<>();
        success.put(false, "Student not found");
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            Classes classes = classesProvider.getAllByTurnusId(student.getTurnus_id());
            List<BasicClass> activeClasses = classes.getActiveClasses();
            BasicClass currentClass = null;
            for (BasicClass classToCheck : activeClasses) {
                if (classToCheck.getCode().equals(code.get("code"))) {
                    currentClass = classToCheck;
                }
            }
            success.put(false, "Not valid code");
            if (currentClass != null) {
                success.remove(false);
                success.put(true, "Successfully attend at class");
                if (currentClass.getClass() == Eloadas.class) {
                    List<Eloadas> eloadasList = student.getEloadasList();
                    eloadasList.add((Eloadas) currentClass);
                    student.setEloadasList(eloadasList);
                } else if (currentClass.getClass() == Gyakorlat.class) {
                    List<Gyakorlat> gyakorlatList = student.getGyakorlatList();
                    gyakorlatList.add((Gyakorlat) currentClass);
                    student.setGyakorlatList(gyakorlatList);
                } else if (currentClass.getClass() == Konzultacio.class) {
                    List<Konzultacio> konzultaciList = student.getKonzultacioList();
                    konzultaciList.add((Konzultacio) currentClass);
                    student.setKonzultacioList(konzultaciList);
                }
                studentRepository.save(student);
            }

        }
        return success;
    }
}