package com.katalogus.project.utility;

import com.katalogus.project.entity.*;
import com.katalogus.project.model.Classes;
import com.katalogus.project.model.StudentStatistic;

import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class AttendancePercentage {

    public HashMap<String, Integer> calculateAttendancePercentages(Student student, Classes classes) {
        HashMap<String, Integer> percentages = new HashMap<>();

        int pointValueOfLectureAtTheSchool = classes.getEloadasList().stream().filter(a -> (!a.getPotlas() && a.getCode() != null) && (a.getAttendanceType().equals(AttendanceType.ALL) || a.getAttendanceType().equals(AttendanceType.GROUP_A))).mapToInt(b -> b.getPoint()).sum();
        int pointValueOfPracticeAtTheSchool = classes.getGyakorlatList().stream().filter(a -> (!a.getPotlas() && a.getCode() != null) && (a.getAttendanceType().equals(AttendanceType.ALL) || a.getAttendanceType().equals(AttendanceType.GROUP_A))).mapToInt(b -> b.getPoint()).sum();
        int pointValueOfConsultationAtTheSchool = classes.getKonzultacioList().stream().filter(a -> (!a.getPotlas() && a.getCode() != null) && (a.getAttendanceType().equals(AttendanceType.ALL) || a.getAttendanceType().equals(AttendanceType.GROUP_A))).mapToInt(b -> b.getPoint()).sum();

        int pointValueOfLectureAtTheStudent = student.getEloadasListByTurnusId(student.getTurnusId()).stream().mapToInt(b -> b.getPoint()).sum();
        int pointValueOfPracticeAtTheStudent = student.getGyakorlatListByTurnusId(student.getTurnusId()).stream().mapToInt(b -> b.getPoint()).sum();
        int pointValueOfConsultationAtTheStudent = student.getKonzultacioListByTurnusId(student.getTurnusId()).stream().mapToInt(b -> b.getPoint()).sum();

        percentages.put("lecture", (int) ((pointValueOfLectureAtTheStudent / (double) pointValueOfLectureAtTheSchool) * 100));
        percentages.put("practice", (int) ((pointValueOfPracticeAtTheStudent / (double) pointValueOfPracticeAtTheSchool) * 100));
        percentages.put("consultation", (int) ((pointValueOfConsultationAtTheStudent / (double) pointValueOfConsultationAtTheSchool) * 100));

        return percentages;
    }

    public List<StudentStatistic> getStudentsStatistics(Turnus turnus, List<Student> studentList, Classes classes) {
        List<StudentStatistic> studentStatisticList = new ArrayList<>();
        for (Student student : studentList) {
            StudentStatistic studentStatistic = StudentStatistic.builder()
                    .studentName(student.getName())
                    .neptunCode(student.getNeptunCode())
                    .percentages(calculateAttendancePercentages(student, classes))
                    .build();
            studentStatistic.createWarning(turnus);
            studentStatisticList.add(studentStatistic);
        }
        return studentStatisticList;
    }
}
