package com.olympics.management.repository;

import com.olympics.management.entity.Epreuve;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EpreuveRepository
        extends JpaRepository<Epreuve, Long> {

    boolean existsByNomIgnoreCaseAndDisciplineId(
            String nom,
            Long disciplineId
    );

    Optional<Epreuve> findByNomIgnoreCaseAndDisciplineId(
            String nom,
            Long disciplineId
    );

    List<Epreuve> findByDisciplineId(Long disciplineId);
}