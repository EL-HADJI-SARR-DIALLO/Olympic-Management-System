package com.olympics.management.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record EpreuveRequest(

        @NotBlank(message = "Le nom de l'épreuve est obligatoire")
        @Size(
                max = 150,
                message = "Le nom de l'épreuve ne doit pas dépasser 150 caractères"
        )
        String nom,

        @Size(
                max = 500,
                message = "La description ne doit pas dépasser 500 caractères"
        )
        String description,

        @NotNull(message = "La date et l'heure sont obligatoires")
        @FutureOrPresent(
                message = "La date de l'épreuve doit être actuelle ou future"
        )
        LocalDateTime dateHeure,

        @NotBlank(message = "Le lieu est obligatoire")
        @Size(
                max = 150,
                message = "Le lieu ne doit pas dépasser 150 caractères"
        )
        String lieu,

        @NotNull(message = "L'identifiant de la discipline est obligatoire")
        @Positive(
                message = "L'identifiant de la discipline doit être positif"
        )
        Long disciplineId
) {
}