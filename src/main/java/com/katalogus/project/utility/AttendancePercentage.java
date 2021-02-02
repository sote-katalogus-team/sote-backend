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

    private HashMap<String, Double> calculateAttendancePercentages(Student student, Long turnusId, List<Eloadas> eloadasList, List<Gyakorlat> gyakorlatList, List<Konzultacio> konzultacioList) {
        HashMap<String, Double> percentages = new HashMap<>();
        percentages.put("lecture", 0.0);
        percentages.put("practice", 0.0);
        percentages.put("consultation", 0.0);

        //Calculate attendance by student id

        return percentages;
    }

    public List<StudentStatistic> getStudentsStatistics(Long turnusId, List<Student> studentList, List<Eloadas> eloadasList, List<Gyakorlat> gyakorlatList, List<Konzultacio> konzultacioList) {
        List<StudentStatistic> studentStatisticList = new ArrayList<>();

        //Create list of StudentStatistics, for all student in a turnus

        return studentStatisticList;
    }
}
