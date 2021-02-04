package com.katalogus.project.utility;

import com.katalogus.project.entity.*;
import com.katalogus.project.model.StudentStatistic;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class AttendancePercentage {

    private HashMap<String, Integer> calculateAttendancePercentages(Student student, Long turnusId, List<Eloadas> eloadasList, List<Gyakorlat> gyakorlatList, List<Konzultacio> konzultacioList) {
        //Calculate attendance by student id
        HashMap<String, Integer> percentages = new HashMap<>();

      /* here was a merge conflict
        int pointValueOfLectureAtTheSchool = eloadasList.stream().filter(a -> a.getTurnus_id() == turnusId).mapToInt(b -> b.getPoint()).sum();
        int pointValueOfPracticeAtTheSchool = gyakorlatList.stream().filter(a -> a.getTurnus_id() == turnusId).mapToInt(b -> b.getPoint()).sum();
        int pointValueOfConsultationAtTheSchool = konzultacioList.stream().filter(a -> a.getTurnus_id() == turnusId).mapToInt(b -> b.getPoint()).sum();

*/
            int pointValueOfLectureAtTheSchool = eloadasList.stream().filter(a -> (a.getTurnus_id() == turnusId && !a.getPotlas())).mapToInt(b -> b.getPoint()).sum();
            int pointValueOfPracticeAtTheSchool = gyakorlatList.stream().filter(a -> (a.getTurnus_id() == turnusId && !a.getPotlas())).mapToInt(b -> b.getPoint()).sum();
            int pointValueOfConsultationAtTheSchool = konzultacioList.stream().filter(a -> (a.getTurnus_id() == turnusId && !a.getPotlas())).mapToInt(b -> b.getPoint()).sum();

        int pointValueOfLectureAtTheStudent = student.getEloadasList().stream().mapToInt(b -> b.getPoint()).sum();
        int pointValueOfPracticeAtTheStudent = student.getGyakorlatList().stream().mapToInt(b -> b.getPoint()).sum();
        int pointValueOfConsultationAtTheStudent = student.getKonzultacioList().stream().mapToInt(b -> b.getPoint()).sum();

        percentages.put("lecture", (int) ((pointValueOfLectureAtTheStudent / (double) pointValueOfLectureAtTheSchool) * 100));
        percentages.put("practice", (int) ((pointValueOfPracticeAtTheStudent / (double) pointValueOfPracticeAtTheSchool) * 100));
        percentages.put("consultation", (int) ((pointValueOfConsultationAtTheStudent / (double) pointValueOfConsultationAtTheSchool) * 100));

        return percentages;
    }

    public List<StudentStatistic> getStudentsStatistics(Turnus turnus, List<Student> studentList, List<Eloadas> eloadasList, List<Gyakorlat> gyakorlatList, List<Konzultacio> konzultacioList) {
        List<StudentStatistic> studentStatisticList = new ArrayList<>();

        //Create list of StudentStatistics, for all student in a turnus
        for (Student student : studentList) {
            if (student.getTurnus_id().equals(turnus.getId())) {
                StudentStatistic studentStatistic = StudentStatistic.builder()
                        .studentName(student.getName())
                        .neptunCode(student.getNeptun_code())
                        .percentages(calculateAttendancePercentages(student, turnus.getId(), eloadasList, gyakorlatList, konzultacioList))
                        .build();
                studentStatistic.createWarning(turnus);
                studentStatisticList.add(studentStatistic);


            }
        }
        return studentStatisticList;
    }
}
