package com.katalogus.project.utility;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationData {

    private String name;

    private String email;

    private String password;

    private String neptunCode;

    private Long turnusId;
}
