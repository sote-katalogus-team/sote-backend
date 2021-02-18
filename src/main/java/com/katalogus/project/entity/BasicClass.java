package com.katalogus.project.entity;

import lombok.Getter;

import java.util.Date;
import java.util.Objects;

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

    private AttendanceType attendaceType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BasicClass)) return false;
        BasicClass that = (BasicClass) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getTurnusId(), that.getTurnusId()) &&
                Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTurnusId(), getName());
    }
}
