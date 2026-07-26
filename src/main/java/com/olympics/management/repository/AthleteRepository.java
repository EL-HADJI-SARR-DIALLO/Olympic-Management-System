package com.olympics.management.repository;

import com.olympics.management.entity.Athlete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AthleteRepository extends JpaRepository<Athlete, Long> {

    boolean existsByEmailIgnoreCase(String email);

    Optional<Athlete> findByEmailIgnoreCase(String email);

    @Query("""
            SELECT COUNT(DISTINCT a.nationalite)
            FROM Athlete a
            WHERE a.nationalite IS NOT NULL
              AND TRIM(a.nationalite) <> ''
            """)
    Long countDistinctNationalites();
}