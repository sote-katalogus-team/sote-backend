package com.katalogus.project.repository;

import com.katalogus.project.entity.Gyakorlat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface GyakorlatRepository extends JpaRepository<Gyakorlat, Long> {

    @Query("SELECT gy FROM Gyakorlat gy WHERE gy.turnusId  = ?1")
    List<Gyakorlat> findAllByTurnusId(Long turnusId);

    List<Gyakorlat> findByDate(Date date);
}
