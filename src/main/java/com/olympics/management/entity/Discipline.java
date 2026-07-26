package com.olympics.management.entity;

import jakarta.persistence.*;

@Entity
@Table(
        name = "disciplines",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_discipline_nom",
                        columnNames = "nom"
                )
        }
)
public class Discipline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nom;

    @Column(length = 500)
    private String description;

    public Discipline() {
    }

    public Discipline(String nom, String description) {
        this.nom = nom;
        this.description = description;
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
}