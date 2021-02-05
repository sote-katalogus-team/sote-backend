package com.katalogus.project.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
