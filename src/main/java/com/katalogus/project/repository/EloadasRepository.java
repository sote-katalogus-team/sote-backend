package com.katalogus.project.repository;

import com.katalogus.project.entity.Eloadas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EloadasRepository extends JpaRepository<Eloadas, Long> {

    @Query("SELECT e FROM Eloadas e WHERE e.turnus_id  = ?1")
    List<Eloadas> findAllByTurnus_id(Long turnusId);
}
