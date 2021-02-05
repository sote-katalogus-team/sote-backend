package com.katalogus.project.entity;

import lombok.Getter;

import java.sql.Date;

@Getter
public abstract class BasicClass {

    private Long id;

    private Long teacher_id = null;

    private Long turnus_id;

    private String name;

    private Date date;

    private Integer point;

    private String code = null;

    private Boolean potlas;

    private Boolean active = true;

    private Boolean isAttendanceOpen = false;
}
