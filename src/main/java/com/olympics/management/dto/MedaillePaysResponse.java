package com.olympics.management.dto;

public record MedaillePaysResponse(
        String nationalite,
        Long nombreMedailles
) {
}