package com.olympics.management.controller;

import com.olympics.management.dto.ClassementPaysResponse;
import com.olympics.management.dto.DashboardResponse;
import com.olympics.management.dto.MedaillePaysResponse;
import com.olympics.management.dto.MedailleStatResponse;
import com.olympics.management.service.DashboardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
@Tag(
        name = "Tableau de bord",
        description = "Statistiques générales des Jeux olympiques"
)
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(
            DashboardService dashboardService
    ) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/athletes/count")
    @Operation(
            summary = "Nombre total d'athlètes",
            description = "Retourne le nombre total d'athlètes enregistrés"
    )
    public ResponseEntity<Map<String, Long>>
    obtenirNombreTotalAthletes() {

        Long nombre =
                dashboardService.obtenirNombreTotalAthletes();

        return ResponseEntity.ok(
                Map.of("nombreTotalAthletes", nombre)
        );
    }

    @GetMapping("/countries/count")
    @Operation(
            summary = "Nombre de pays participants",
            description = "Retourne le nombre de nationalités différentes"
    )
    public ResponseEntity<Map<String, Long>>
    obtenirNombrePaysParticipants() {

        Long nombre =
                dashboardService.obtenirNombrePaysParticipants();

        return ResponseEntity.ok(
                Map.of("nombrePaysParticipants", nombre)
        );
    }

    @GetMapping("/medals/count")
    @Operation(
            summary = "Nombre de médailles",
            description = "Retourne le nombre de médailles d'or, d'argent et de bronze"
    )
    public ResponseEntity<MedailleStatResponse>
    obtenirStatistiquesMedailles() {

        return ResponseEntity.ok(
                dashboardService.obtenirStatistiquesMedailles()
        );
    }

    @GetMapping("/countries/ranking")
    @Operation(
            summary = "Classement des pays par points",
            description = """
                    Classe les pays selon le barème :
                    Or = 7 points,
                    Argent = 4 points,
                    Bronze = 1 point
                    """
    )
    public ResponseEntity<List<ClassementPaysResponse>>
    obtenirClassementPays() {

        return ResponseEntity.ok(
                dashboardService.obtenirClassementPays()
        );
    }

    @GetMapping("/medalists-by-country")
    @Operation(
            summary = "Nombre de médaillés par pays",
            description = """
                    Retourne le nombre d'athlètes différents
                    ayant obtenu une médaille pour chaque pays
                    """
    )
    public ResponseEntity<List<MedaillePaysResponse>>
    obtenirNombreMedaillesParPays() {

        return ResponseEntity.ok(
                dashboardService
                        .obtenirNombreMedaillesParPays()
        );
    }

    @GetMapping("/summary")
    @Operation(
            summary = "Tableau de bord complet",
            description = "Retourne toutes les statistiques dans une seule réponse"
    )
    public ResponseEntity<DashboardResponse>
    obtenirTableauDeBord() {

        return ResponseEntity.ok(
                dashboardService.obtenirTableauDeBord()
        );
    }
}