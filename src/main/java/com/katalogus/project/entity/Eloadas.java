package com.katalogus.project.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Eloadas extends BasicClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long turnusId;

    private String name;

    @Temporal(TemporalType.DATE)
    private Date date;

    private Integer point;

    private Boolean potlas;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private AttendanceType attendaceType = AttendanceType.ALL;

    @Builder.Default
    private String code = null;

    @Builder.Default
    private Boolean active = true;

    @Builder.Default
    private Boolean isAttendanceOpen = false;
}
