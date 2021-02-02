package com.katalogus.project.utility;

import com.katalogus.project.entity.Eloadas;
import com.katalogus.project.entity.Gyakorlat;
import com.katalogus.project.entity.Konzultacio;
import com.katalogus.project.entity.Student;
import com.katalogus.project.model.StudentStatistic;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AttendancePercentage {

    private HashMap<String, Integer> calculateAttendancePercentages(Student student, Long turnusId, List<Eloadas> eloadasList, List<Gyakorlat> gyakorlatList, List<Konzultacio> konzultacioList) {
        //Calculate attendance by student id
        HashMap<String, Integer> percentages = new HashMap<>();

            int pointValueOfLectureAtTheSchool = eloadasList.stream().filter(a -> a.getTurnus_id() == turnusId).mapToInt(b -> b.getPoint()).sum();
            int pointValueOfPracticeAtTheSchool = gyakorlatList.stream().filter(a -> a.getTurnus_id() == turnusId).mapToInt(b -> b.getPoint()).sum();
            int pointValueOfConsultationAtTheSchool = konzultacioList.stream().filter(a -> a.getTurnus_id() == turnusId).mapToInt(b -> b.getPoint()).sum();

            int pointValueOfLectureAtTheStudent = student.getEloadasList().stream().mapToInt(b -> b.getPoint()).sum();
            int pointValueOfPracticeAtTheStudent = student.getGyakorlatList().stream().mapToInt(b -> b.getPoint()).sum();
            int pointValueOfConsultationAtTheStudent = student.getKonzultacioList().stream().mapToInt(b -> b.getPoint()).sum();

            percentages.put("lecture", (int) ((pointValueOfLectureAtTheStudent / (double) pointValueOfLectureAtTheSchool) * 100));
            percentages.put("practice", (int) ((pointValueOfPracticeAtTheStudent / (double) pointValueOfPracticeAtTheSchool) * 100));
            percentages.put("consultation", (int) ((pointValueOfConsultationAtTheStudent / (double) pointValueOfConsultationAtTheSchool) * 100));

        return percentages;
    }

    public List<StudentStatistic> getStudentsStatistics(Long turnusId, List<Student> studentList, List<Eloadas> eloadasList, List<Gyakorlat> gyakorlatList, List<Konzultacio> konzultacioList) {
        List<StudentStatistic> studentStatisticList = new ArrayList<>();

        //Create list of StudentStatistics, for all student in a turnus
        for (Student student : studentList) {
            if (student.getTurnus_id() == turnusId) {
                  studentStatisticList.add(new StudentStatistic(student.getName(),student.getNeptun_code(),calculateAttendancePercentages(student,turnusId,eloadasList,gyakorlatList,konzultacioList)));
            }
        }
        return studentStatisticList;
    }
}
