package com.katalogus.project.model;

import com.katalogus.project.entity.BasicClass;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ClassAttendance {

    BasicClass currentClass;

    Integer headCount;
}
