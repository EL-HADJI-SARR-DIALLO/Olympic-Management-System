package com.olympics.management.dto;

import com.olympics.management.enums.Sexe;

import java.time.LocalDate;

public record AthleteResponse(
        Long id,
        String nom,
        String prenom,
        LocalDate dateNaissance,
        Sexe sexe,
        String nationalite,
        String email,
        Long disciplineId,
        String disciplineNom
) {
}