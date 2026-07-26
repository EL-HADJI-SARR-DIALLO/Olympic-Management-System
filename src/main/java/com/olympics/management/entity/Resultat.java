package com.olympics.management.entity;

import com.olympics.management.enums.Medaille;
import jakarta.persistence.*;

@Entity
@Table(
        name = "resultats",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_resultat_athlete_epreuve",
                        columnNames = {"athlete_id", "epreuve_id"}
                )
        }
)
public class Resultat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer classement;

    @Column(length = 100)
    private String performance;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Medaille medaille;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "athlete_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_resultat_athlete")
    )
    private Athlete athlete;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "epreuve_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_resultat_epreuve")
    )
    private Epreuve epreuve;

    public Resultat() {
    }

    public Long getId() {
        return id;
    }

    public Integer getClassement() {
        return classement;
    }

    public void setClassement(Integer classement) {
        this.classement = classement;
    }

    public String getPerformance() {
        return performance;
    }

    public void setPerformance(String performance) {
        this.performance = performance;
    }

    public Medaille getMedaille() {
        return medaille;
    }

    public void setMedaille(Medaille medaille) {
        this.medaille = medaille;
    }

    public Athlete getAthlete() {
        return athlete;
    }

    public void setAthlete(Athlete athlete) {
        this.athlete = athlete;
    }

    public Epreuve getEpreuve() {
        return epreuve;
    }

    public void setEpreuve(Epreuve epreuve) {
        this.epreuve = epreuve;
    }
}