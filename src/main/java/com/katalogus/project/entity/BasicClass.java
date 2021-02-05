package com.katalogus.project.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;

@Getter
public abstract class BasicClass {

    @Id
    @GeneratedValue
    private Long id;

    @Builder.Default
    private Long teacher_id = null;

    private Long turnus_id;

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
