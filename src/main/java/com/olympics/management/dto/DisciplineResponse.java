package com.olympics.management.dto;

public record DisciplineResponse(
        Long id,
        String nom,
        String description
) {
}