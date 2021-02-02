package com.katalogus.project.repository;

import com.katalogus.project.entity.Eloadas;
import com.katalogus.project.entity.Konzultacio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KonzultacioRepository extends JpaRepository<Konzultacio, Long> {

    List<Konzultacio> findAllByTurnus_id(Long turnusId);
}
