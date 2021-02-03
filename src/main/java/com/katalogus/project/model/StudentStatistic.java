package com.katalogus.project.model;

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

    private HashMap<String, Integer> percentages = new HashMap<>();

    private String warning;

}