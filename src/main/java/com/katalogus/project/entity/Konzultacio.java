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
public class Konzultacio {

    @Id
    @GeneratedValue
    private Long id;

    @Builder.Default
    private Long teacher_id = null;

    private Long turnus_id;

    private String name;

    private Integer point;

    private Boolean potlas;

    @Builder.Default
    private String code = null;

    private Date date;

    @Builder.Default
    private Boolean active = true;
}
