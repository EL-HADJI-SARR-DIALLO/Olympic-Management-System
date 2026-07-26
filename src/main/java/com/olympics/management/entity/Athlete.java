package com.olympics.management.entity;

import com.olympics.management.enums.Sexe;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(
        name = "athletes",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_athlete_email",
                        columnNames = "email"
                )
        }
)
public class Athlete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nom;

    @Column(nullable = false, length = 100)
    private String prenom;

    @Column(nullable = false)
    private LocalDate dateNaissance;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Sexe sexe;

    @Column(nullable = false, length = 100)
    private String nationalite;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "discipline_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_athlete_discipline")
    )
    private Discipline discipline;

    public Athlete() {
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Sexe getSexe() {
        return sexe;
    }

    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }
}