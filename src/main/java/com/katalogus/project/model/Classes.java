package com.katalogus.project.model;

import com.katalogus.project.entity.Eloadas;
import com.katalogus.project.entity.Gyakorlat;
import com.katalogus.project.entity.Konzultacio;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class Classes {

    private List<Eloadas> eloadasList = new ArrayList<>();

    private List<Gyakorlat> gyakorlatList = new ArrayList<>();

    private List<Konzultacio> konzultacioList = new ArrayList<>();
}
