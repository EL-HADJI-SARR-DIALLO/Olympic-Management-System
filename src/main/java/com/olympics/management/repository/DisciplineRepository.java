package com.olympics.management.repository;

import com.olympics.management.entity.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DisciplineRepository
        extends JpaRepository<Discipline, Long> {

    boolean existsByNomIgnoreCase(String nom);

    Optional<Discipline> findByNomIgnoreCase(String nom);
}