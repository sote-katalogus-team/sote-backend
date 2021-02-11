package com.katalogus.project.repository;

import com.katalogus.project.entity.Turnus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TurnusRepository extends JpaRepository<Turnus, Long> {
    public List<Turnus> findAllByYear(Integer year);
}
