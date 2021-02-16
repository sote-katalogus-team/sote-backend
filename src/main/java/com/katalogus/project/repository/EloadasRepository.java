package com.katalogus.project.repository;

import com.katalogus.project.entity.Eloadas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface EloadasRepository extends JpaRepository<Eloadas, Long> {

    @Query("SELECT e FROM Eloadas e WHERE e.turnusId  = ?1")
    List<Eloadas> findAllByTurnusId(Long turnusId);

    List<Eloadas> findByDate(Date date);

    List<Eloadas> findAllByTurnusIdAndPotlasIsFalse(Long turnusId);
}
