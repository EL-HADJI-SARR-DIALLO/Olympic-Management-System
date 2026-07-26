package com.olympics.management.dto;

import java.util.List;

public record DashboardResponse(
        Long nombreTotalAthletes,
        Long nombrePaysParticipants,
        MedailleStatResponse medailles,
        List<ClassementPaysResponse> classementPays,
        List<MedaillePaysResponse> medaillesParPays
) {
}