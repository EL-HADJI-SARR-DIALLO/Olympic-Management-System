package com.olympics.management.repository;

import com.olympics.management.dto.TableauMedailleProjection;
import com.olympics.management.entity.Resultat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.olympics.management.repository.projection.ClassementPaysProjection;
import com.olympics.management.repository.projection.MedaillePaysProjection;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import java.util.List;
import java.util.Optional;


public interface ResultatRepository
        extends JpaRepository<Resultat, Long> {

    boolean existsByAthleteIdAndEpreuveId(
            Long athleteId,
            Long epreuveId
    );

    Optional<Resultat> findByAthleteIdAndEpreuveId(
            Long athleteId,
            Long epreuveId
    );

    List<Resultat> findByAthleteId(Long athleteId);

    List<Resultat> findByEpreuveIdOrderByClassementAsc(
            Long epreuveId
    );

    @Query("""
            SELECT
                a.nationalite AS nationalite,
                SUM(CASE
                    WHEN r.medaille = com.olympics.management.enums.Medaille.OR
                    THEN 1 ELSE 0
                END) AS nombreOr,
                SUM(CASE
                    WHEN r.medaille = com.olympics.management.enums.Medaille.ARGENT
                    THEN 1 ELSE 0
                END) AS nombreArgent,
                SUM(CASE
                    WHEN r.medaille = com.olympics.management.enums.Medaille.BRONZE
                    THEN 1 ELSE 0
                END) AS nombreBronze,
                SUM(CASE
                    WHEN r.medaille <> com.olympics.management.enums.Medaille.AUCUNE
                    THEN 1 ELSE 0
                END) AS total
            FROM Resultat r
            JOIN r.athlete a
            WHERE r.medaille <> com.olympics.management.enums.Medaille.AUCUNE
            GROUP BY a.nationalite
            ORDER BY
                SUM(CASE
                    WHEN r.medaille = com.olympics.management.enums.Medaille.OR
                    THEN 1 ELSE 0
                END) DESC,
                SUM(CASE
                    WHEN r.medaille = com.olympics.management.enums.Medaille.ARGENT
                    THEN 1 ELSE 0
                END) DESC,
                SUM(CASE
                    WHEN r.medaille = com.olympics.management.enums.Medaille.BRONZE
                    THEN 1 ELSE 0
                END) DESC
            """)

    List<TableauMedailleProjection> calculerTableauMedailles();

    @Query("""
        SELECT COUNT(r)
        FROM Resultat r
        WHERE r.medaille = com.olympics.management.enums.Medaille.OR
        """)
    Long countMedaillesOr();

    @Query("""
        SELECT COUNT(r)
        FROM Resultat r
        WHERE r.medaille = com.olympics.management.enums.Medaille.ARGENT
        """)
    Long countMedaillesArgent();

    @Query("""
        SELECT COUNT(r)
        FROM Resultat r
        WHERE r.medaille = com.olympics.management.enums.Medaille.BRONZE
        """)
    Long countMedaillesBronze();

    @Query("""
        SELECT
            r.athlete.nationalite AS nationalite,

            SUM(
                CASE
                    WHEN r.medaille = com.olympics.management.enums.Medaille.OR
                    THEN 1
                    ELSE 0
                END
            ) AS nombreOr,

            SUM(
                CASE
                    WHEN r.medaille = com.olympics.management.enums.Medaille.ARGENT
                    THEN 1
                    ELSE 0
                END
            ) AS nombreArgent,

            SUM(
                CASE
                    WHEN r.medaille = com.olympics.management.enums.Medaille.BRONZE
                    THEN 1
                    ELSE 0
                END
            ) AS nombreBronze,

            SUM(
                CASE
                    WHEN r.medaille IN (
                        com.olympics.management.enums.Medaille.OR,
                        com.olympics.management.enums.Medaille.ARGENT,
                        com.olympics.management.enums.Medaille.BRONZE
                    )
                    THEN 1
                    ELSE 0
                END
            ) AS totalMedailles,

            SUM(
                CASE
                    WHEN r.medaille = com.olympics.management.enums.Medaille.OR
                    THEN 7
                    WHEN r.medaille = com.olympics.management.enums.Medaille.ARGENT
                    THEN 4
                    WHEN r.medaille = com.olympics.management.enums.Medaille.BRONZE
                    THEN 1
                    ELSE 0
                END
            ) AS nombrePoints

        FROM Resultat r

        WHERE r.medaille <> com.olympics.management.enums.Medaille.AUCUNE
          AND r.athlete.nationalite IS NOT NULL

        GROUP BY r.athlete.nationalite

        ORDER BY nombrePoints DESC,
                 nombreOr DESC,
                 nombreArgent DESC,
                 nombreBronze DESC
        """)
    List<ClassementPaysProjection> calculerClassementPaysParPoints();

    @Query("""
        SELECT
            r.athlete.nationalite AS nationalite,
            COUNT(DISTINCT r.athlete.id) AS nombreMedailles

        FROM Resultat r

        WHERE r.medaille <> com.olympics.management.enums.Medaille.AUCUNE
          AND r.athlete.nationalite IS NOT NULL

        GROUP BY r.athlete.nationalite

        ORDER BY nombreMedailles DESC
        """)
    List<MedaillePaysProjection> compterMedaillesParPays();
}