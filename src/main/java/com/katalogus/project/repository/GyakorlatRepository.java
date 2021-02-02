package com.katalogus.project.repository;

import com.katalogus.project.entity.Eloadas;
import com.katalogus.project.entity.Gyakorlat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GyakorlatRepository extends JpaRepository<Gyakorlat, Long> {

    List<Gyakorlat> findAllByTurnus_id(Long turnusId);
}
