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

    private Long turnusId;

    private String validationCode;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private List<ApplicationUserRole> roles;

    @ManyToMany
    private List<Konzultacio> konzultacioList;

    @ManyToMany
    private List<Eloadas> eloadasList;

    @ManyToMany
    private List<Gyakorlat> gyakorlatList;

    public boolean wasInClass(BasicClass currentClass) {
        return konzultacioList.contains(currentClass) || eloadasList.contains(currentClass) || gyakorlatList.contains(currentClass);
    }

    public void addClass(BasicClass currentClass){
        if (currentClass.getClass() == Eloadas.class) {
            eloadasList.add((Eloadas) currentClass);
        } else if (currentClass.getClass() == Gyakorlat.class) {
            gyakorlatList.add((Gyakorlat) currentClass);
        } else if (currentClass.getClass() == Konzultacio.class) {
            konzultacioList.add((Konzultacio) currentClass);
        }
    }

}
