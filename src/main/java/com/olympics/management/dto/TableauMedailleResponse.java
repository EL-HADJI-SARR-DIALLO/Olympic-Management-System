package com.olympics.management.dto;

public record TableauMedailleResponse(
        Integer rang,
        String nationalite,
        Long nombreOr,
        Long nombreArgent,
        Long nombreBronze,
        Long total
) {
}