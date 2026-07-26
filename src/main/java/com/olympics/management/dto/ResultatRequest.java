package com.olympics.management.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ResultatRequest(

        @NotNull(message = "Le classement est obligatoire")
        @Positive(message = "Le classement doit être supérieur à zéro")
        Integer classement,

        @Size(
                max = 100,
                message = "La performance ne doit pas dépasser 100 caractères"
        )
        String performance,

        @NotNull(message = "L'identifiant de l'athlète est obligatoire")
        @Positive(message = "L'identifiant de l'athlète doit être positif")
        Long athleteId,

        @NotNull(message = "L'identifiant de l'épreuve est obligatoire")
        @Positive(message = "L'identifiant de l'épreuve doit être positif")
        Long epreuveId
) {
}