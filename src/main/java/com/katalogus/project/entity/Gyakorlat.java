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

    @Builder.Default
    private Long teacher_id = null;

    private Long turnus_id;

    private String name;

    @Builder.Default
    private String code = null;

    private Date date;

    private Boolean potlas;

    private Integer point;

    @Builder.Default
    private Boolean active = true;

}
