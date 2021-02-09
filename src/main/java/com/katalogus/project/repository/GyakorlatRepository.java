package com.katalogus.project.repository;

import com.katalogus.project.entity.Gyakorlat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface GyakorlatRepository extends JpaRepository<Gyakorlat, Long> {

    @Query("SELECT gy FROM Gyakorlat gy WHERE gy.turnus_id  = ?1")
    List<Gyakorlat> findAllByTurnus_id(Long turnusId);

    List<Gyakorlat> findByDate(Date date);
}
