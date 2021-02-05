package com.katalogus.project.model;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ClassInfo {

    private ClassType type;

    private Long id;
}
