package com.katalogus.project.entity;

import com.katalogus.project.security.ApplicationUserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Student extends ApplicationUser {


    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String email;

    private String password;

    private String neptunCode;

    private Long turnus_id;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private List<ApplicationUserRole> roles;

    @OneToMany
    private List<Konzultacio> konzultacioList;

    @OneToMany
    private List<Eloadas> eloadasList;

    @OneToMany
    private List<Gyakorlat> gyakorlatList;

}
