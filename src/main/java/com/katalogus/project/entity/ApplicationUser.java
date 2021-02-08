package com.katalogus.project.entity;

import com.katalogus.project.security.ApplicationUserRole;
import lombok.Getter;

import java.util.List;

@Getter
public abstract class ApplicationUser {

    private Long id;

    private String name;

    private String email;

    private String password;

    private List<ApplicationUserRole> roles;
}
