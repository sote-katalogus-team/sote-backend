package com.katalogus.project.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.*;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Eloadas {

    @Id
    @GeneratedValue
    private Long id;

    private Long teacher_id;

    private Long turnus_id;

    private String name;

    private Date date;

    private String code;

    private Integer point;

    private Boolean potlas;

    @Builder.Default
    private Boolean active = true;

}
