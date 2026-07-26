package com.olympics.management.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DisciplineRequest(

        @NotBlank(message = "Le nom de la discipline est obligatoire")
        @Size(max = 100, message = "Le nom ne doit pas dépasser 100 caractères")
        String nom,

        @Size(max = 500, message = "La description ne doit pas dépasser 500 caractères")
        String description
) {
}