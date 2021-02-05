package com.katalogus.project.model;

import com.katalogus.project.entity.BasicClass;
import com.katalogus.project.entity.Eloadas;
import com.katalogus.project.entity.Gyakorlat;
import com.katalogus.project.entity.Konzultacio;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Classes {

    private List<Eloadas> eloadasList = new ArrayList<>();

    private List<Gyakorlat> gyakorlatList = new ArrayList<>();

    private List<Konzultacio> konzultacioList = new ArrayList<>();

    public List<BasicClass> getActiveClasses() {
        List<BasicClass> basicClassList = new ArrayList<>();

        eloadasList.forEach(eloadas -> {
            if (eloadas.getIsAttendanceOpen()) {
                basicClassList.add(eloadas);
            }
        });
        gyakorlatList.forEach(gyakorlat -> {
            if (gyakorlat.getIsAttendanceOpen()) {
                basicClassList.add(gyakorlat);
            }
        });
        konzultacioList.forEach(konzultacio -> {
            if (konzultacio.getIsAttendanceOpen()) {
                basicClassList.add(konzultacio);
            }
        });
        return basicClassList;
    }

}
