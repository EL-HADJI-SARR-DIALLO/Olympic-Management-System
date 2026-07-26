package com.olympics.management.service;

import com.olympics.management.dto.ClassementPaysResponse;
import com.olympics.management.dto.DashboardResponse;
import com.olympics.management.dto.MedaillePaysResponse;
import com.olympics.management.dto.MedailleStatResponse;

import java.util.List;

public interface DashboardService {

    Long obtenirNombreTotalAthletes();

    Long obtenirNombrePaysParticipants();

    MedailleStatResponse obtenirStatistiquesMedailles();

    List<ClassementPaysResponse> obtenirClassementPays();

    List<MedaillePaysResponse> obtenirNombreMedaillesParPays();

    DashboardResponse obtenirTableauDeBord();
}