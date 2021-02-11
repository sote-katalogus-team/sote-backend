package com.katalogus.project.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Year;
import java.util.Date;

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

    private Integer year;

    private String combinedName;

    @Builder.Default
    private Integer lecture = 0;

    @Builder.Default
    private Integer practice = 0;

    @Builder.Default
    private Integer consultation = 0;
}
