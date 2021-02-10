package com.katalogus.project.model;

import com.katalogus.project.entity.Turnus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class StudentStatistic {

    private String studentName;

    private String neptunCode;

    private HashMap<String, Integer> percentages;

    private String warning;

    public void createWarning(Turnus turnus) {
        if (percentages.get("lecture") < turnus.getLecture()) {
            warning = "Előadás";
        }
        if (percentages.get("practice") < turnus.getPractice()) {
            if (warning == null) {
                warning = "Gyakorlat";
            } else {
                warning = warning.concat(", Gyakorlat");
            }
        }
        if (percentages.get("consultation") < turnus.getConsultation()) {
            if (warning == null) {
                warning = "Konzultáció";
            } else {
                warning = warning.concat(", Konzultáció");
            }
        }
    }

}