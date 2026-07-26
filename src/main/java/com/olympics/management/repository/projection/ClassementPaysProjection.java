package com.olympics.management.repository.projection;

public interface ClassementPaysProjection {

    String getNationalite();

    Long getNombreOr();

    Long getNombreArgent();

    Long getNombreBronze();

    Long getTotalMedailles();

    Long getNombrePoints();
}