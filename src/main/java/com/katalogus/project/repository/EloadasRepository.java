package com.katalogus.project.repository;

import com.katalogus.project.entity.Eloadas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EloadasRepository extends JpaRepository<Eloadas, Long> {

    List<Eloadas> findAllByTurnus_id(Long turnusId);
}
