package com.katalogus.project.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Turnus {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Integer turnus_number;

    private Double lecture;

    private Double practice;

    private Double consultation;
}
