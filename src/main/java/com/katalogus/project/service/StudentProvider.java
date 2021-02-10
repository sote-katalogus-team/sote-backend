package com.katalogus.project.service;

import com.katalogus.project.entity.*;
import com.katalogus.project.model.ClassInfo;
import com.katalogus.project.model.ClassType;
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
            Classes classes = classesProvider.getAllByTurnusId(student.getTurnusId());
            return StudentStatistic.builder()
                    .percentages(attendancePercentage.calculateAttendancePercentages(student, classes))
                    .neptunCode(student.getNeptunCode())
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
            Classes classes = classesProvider.getAllByTurnusId(student.getTurnusId());
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

    public Integer countStudentAtClass(ClassInfo classInfo) {
        Integer count = 0;
        List<Student> studentList = studentRepository.findAll();
        for (Student currentStudent : studentList) {
            if (classInfo.getType() == ClassType.ELOADAS) {
                for (Eloadas eloadas : currentStudent.getEloadasList()) {
                    if (eloadas.getId().equals(classInfo.getId())) {
                        count++;
                    }
                }
            } else if (classInfo.getType() == ClassType.GYAKORLAT) {
                for (Gyakorlat gyakorlat : currentStudent.getGyakorlatList()) {
                    if (gyakorlat.getId().equals(classInfo.getId())) {
                        count++;
                    }
                }
            } else {
                for (Konzultacio konzultacio : currentStudent.getKonzultacioList()) {
                    if (konzultacio.getId().equals(classInfo.getId())) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public HashMap<Boolean, String> addByNeptunCode(HashMap<String, String> neptunCode, ClassInfo classInfo) {
        HashMap<Boolean, String> success = new HashMap<>();
        String neptunString = neptunCode.get("neptunCode");
        success.put(false, "No registered student with " + neptunString);
        Optional<Student> optionalStudent = studentRepository.findByNeptunCodeIgnoreCase(neptunString);
        if (optionalStudent.isPresent()) {
            success.put(false, "No class found");
            Classes classes = classesProvider.getAllClasses();
            Student student = optionalStudent.get();
            if (classInfo.getType() == ClassType.ELOADAS) {
                for (Eloadas eloadas : classes.getEloadasList()) {
                    if (eloadas.getId().equals(classInfo.getId())) {
                        List<Eloadas> eloadasList = student.getEloadasList();
                        eloadasList.add(eloadas);
                        student.setEloadasList(eloadasList);
                        success.remove(false);
                        success.put(true, student.getName() + "was added to class!");
                    }
                }
            } else if (classInfo.getType() == ClassType.GYAKORLAT) {
                for (Gyakorlat gyakorlat : classes.getGyakorlatList()) {
                    if (gyakorlat.getId().equals(classInfo.getId())) {
                        List<Gyakorlat> gyakorlatList = student.getGyakorlatList();
                        gyakorlatList.add(gyakorlat);
                        student.setGyakorlatList(gyakorlatList);
                        success.remove(false);
                        success.put(true, student.getName() + "was added to class!");
                    }
                }
            } else {
                for (Konzultacio konzultacio : classes.getKonzultacioList()) {
                    if (konzultacio.getId().equals(classInfo.getId())) {
                        List<Konzultacio> konzultacioList = student.getKonzultacioList();
                        konzultacioList.add(konzultacio);
                        student.setKonzultacioList(konzultacioList);
                        success.remove(false);
                        success.put(true, student.getName() + "was added to class!");
                    }
                }
            }
            studentRepository.save(student);
        }
        return success;
    }

    public Integer getHeadCount(Long turnusId) {
        Integer headCount = studentRepository.countByTurnusId(turnusId);
        if (headCount < 1) {
            return -1;
        } else {
            return headCount;
        }

    }
}