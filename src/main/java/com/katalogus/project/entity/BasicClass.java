package com.katalogus.project.entity;

import lombok.Getter;

import java.util.Date;

@Getter
public abstract class BasicClass {

    private Long id;

    private Long turnusId;

    private String name;

    private Date date;

    private Integer point;

    private String code = null;

    private Boolean potlas;

    private Boolean active = true;

    private Boolean isAttendanceOpen = false;
}
