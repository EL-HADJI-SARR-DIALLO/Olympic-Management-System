package com.olympics.management.dto;

import com.olympics.management.enums.Medaille;

public record ResultatResponse(
        Long id,
        Integer classement,
        String performance,
        Medaille medaille,
        Long athleteId,
        String athleteNomComplet,
        String nationalite,
        Long epreuveId,
        String epreuveNom
) {
}