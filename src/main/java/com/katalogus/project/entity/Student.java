package com.katalogus.project.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Student {


    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String email;

    private String password;

    private String neptunCode;

    private Long turnus_id;

    @OneToMany
    private List<Konzultacio> konzultacioList;

    @OneToMany
    private List<Eloadas> eloadasList;

    @OneToMany
    private List<Gyakorlat> gyakorlatList;

}
