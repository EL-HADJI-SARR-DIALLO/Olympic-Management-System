package com.olympics.management.service.impl;

import com.olympics.management.dto.ClassementPaysResponse;
import com.olympics.management.dto.DashboardResponse;
import com.olympics.management.dto.MedaillePaysResponse;
import com.olympics.management.dto.MedailleStatResponse;
import com.olympics.management.repository.AthleteRepository;
import com.olympics.management.repository.ResultatRepository;
import com.olympics.management.repository.projection.ClassementPaysProjection;
import com.olympics.management.repository.projection.MedaillePaysProjection;
import com.olympics.management.service.DashboardService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class DashboardServiceImpl implements DashboardService {

    private final AthleteRepository athleteRepository;
    private final ResultatRepository resultatRepository;

    public DashboardServiceImpl(
            AthleteRepository athleteRepository,
            ResultatRepository resultatRepository
    ) {
        this.athleteRepository = athleteRepository;
        this.resultatRepository = resultatRepository;
    }

    @Override
    public Long obtenirNombreTotalAthletes() {
        return athleteRepository.count();
    }

    @Override
    public Long obtenirNombrePaysParticipants() {
        Long nombrePays = athleteRepository.countDistinctNationalites();

        return nombrePays == null ? 0L : nombrePays;
    }

    @Override
    public MedailleStatResponse obtenirStatistiquesMedailles() {

        Long nombreOr = valeurOuZero(
                resultatRepository.countMedaillesOr()
        );

        Long nombreArgent = valeurOuZero(
                resultatRepository.countMedaillesArgent()
        );

        Long nombreBronze = valeurOuZero(
                resultatRepository.countMedaillesBronze()
        );

        Long total = nombreOr + nombreArgent + nombreBronze;

        return new MedailleStatResponse(
                nombreOr,
                nombreArgent,
                nombreBronze,
                total
        );
    }

    @Override
    public List<ClassementPaysResponse> obtenirClassementPays() {

        List<ClassementPaysProjection> projections =
                resultatRepository.calculerClassementPaysParPoints();

        List<ClassementPaysResponse> classement =
                new ArrayList<>();

        for (int index = 0; index < projections.size(); index++) {

            ClassementPaysProjection projection =
                    projections.get(index);

            ClassementPaysResponse ligne =
                    new ClassementPaysResponse(
                            index + 1,
                            projection.getNationalite(),
                            valeurOuZero(projection.getNombreOr()),
                            valeurOuZero(projection.getNombreArgent()),
                            valeurOuZero(projection.getNombreBronze()),
                            valeurOuZero(projection.getTotalMedailles()),
                            valeurOuZero(projection.getNombrePoints())
                    );

            classement.add(ligne);
        }

        return classement;
    }

    @Override
    public List<MedaillePaysResponse>
    obtenirNombreMedaillesParPays() {

        return resultatRepository
                .compterMedaillesParPays()
                .stream()
                .map(this::convertirMedaillePays)
                .toList();
    }

    @Override
    public DashboardResponse obtenirTableauDeBord() {

        return new DashboardResponse(
                obtenirNombreTotalAthletes(),
                obtenirNombrePaysParticipants(),
                obtenirStatistiquesMedailles(),
                obtenirClassementPays(),
                obtenirNombreMedaillesParPays()
        );
    }

    private MedaillePaysResponse convertirMedaillePays(
            MedaillePaysProjection projection
    ) {
        return new MedaillePaysResponse(
                projection.getNationalite(),
                valeurOuZero(projection.getNombreMedailles())
        );
    }

    private Long valeurOuZero(Long valeur) {
        return valeur == null ? 0L : valeur;
    }
}