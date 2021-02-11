package com.katalogus.project.model;

import lombok.*;

@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ManualAttendance {

    private ClassInfo classInfo;

    private String neptunCode;
}
