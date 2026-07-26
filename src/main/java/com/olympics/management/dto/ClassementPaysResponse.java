package com.olympics.management.dto;

public record ClassementPaysResponse(
        Integer rang,
        String nationalite,
        Long nombreOr,
        Long nombreArgent,
        Long nombreBronze,
        Long totalMedailles,
        Long nombrePoints
) {
}