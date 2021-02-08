package com.katalogus.project.repository;

import com.katalogus.project.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByNeptunCode(String neptunCode);

    Optional<Student> findByEmail(String email);
}
