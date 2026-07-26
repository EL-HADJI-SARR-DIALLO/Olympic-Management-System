package com.olympics.management.dto;

public record MedailleStatResponse(
        Long nombreOr,
        Long nombreArgent,
        Long nombreBronze,
        Long total
) {
}