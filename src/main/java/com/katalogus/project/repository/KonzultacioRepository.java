package com.katalogus.project.repository;

import com.katalogus.project.entity.Eloadas;
import com.katalogus.project.entity.Konzultacio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface KonzultacioRepository extends JpaRepository<Konzultacio, Long> {

    @Query("SELECT k FROM Konzultacio k WHERE k.turnusId  = ?1")
    List<Konzultacio> findAllByTurnusId(Long turnusId);

    List<Konzultacio> findByDate(Date date);

    List<Konzultacio> findAllByTurnusIdAndPotlasIsFalse(Long turnusId);
}
