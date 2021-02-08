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
public class Konzultacio extends BasicClass {

    @Id
    @GeneratedValue
    private Long id;

    private Long turnusId;

    private String name;

    private Date date;

    private Integer point;

    @Builder.Default
    private String code = null;

    private Boolean potlas;

    @Builder.Default
    private Boolean active = true;

    @Builder.Default
    private Boolean isAttendanceOpen = false;
}
