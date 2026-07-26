package com.olympics.management.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "epreuves",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_epreuve_nom_discipline",
                        columnNames = {"nom", "discipline_id"}
                )
        }
)
public class Epreuve {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nom;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private LocalDateTime dateHeure;

    @Column(nullable = false, length = 150)
    private String lieu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "discipline_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_epreuve_discipline")
    )
    private Discipline discipline;

    public Epreuve() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateHeure() {
        return dateHeure;
    }

    public void setDateHeure(LocalDateTime dateHeure) {
        this.dateHeure = dateHeure;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }
}