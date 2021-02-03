package com.katalogus.project.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Gyakorlat {

    @Id
    @GeneratedValue
    private Long id;

    private Long teacher_id;

    private Long turnus_id;

    private String name;

    private String code;

    private Date date;

    private Boolean potlas;

    private Integer point;

    @Builder.Default
    private Boolean active = true;

}
