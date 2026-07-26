package com.olympics.management.dto;

import java.time.LocalDateTime;

public record EpreuveResponse(
        Long id,
        String nom,
        String description,
        LocalDateTime dateHeure,
        String lieu,
        Long disciplineId,
        String disciplineNom
) {
}