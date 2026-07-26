package com.olympics.management.mapper;

import com.olympics.management.dto.ResultatRequest;
import com.olympics.management.dto.ResultatResponse;
import com.olympics.management.entity.Athlete;
import com.olympics.management.entity.Epreuve;
import com.olympics.management.entity.Resultat;
import com.olympics.management.enums.Medaille;
import org.springframework.stereotype.Component;

@Component
public class ResultatMapper {

    public Resultat toEntity(
            ResultatRequest request,
            Athlete athlete,
            Epreuve epreuve
    ) {
        Resultat resultat = new Resultat();

        resultat.setClassement(request.classement());
        resultat.setPerformance(request.performance());
        resultat.setMedaille(calculerMedaille(request.classement()));
        resultat.setAthlete(athlete);
        resultat.setEpreuve(epreuve);

        return resultat;
    }

    public ResultatResponse toResponse(Resultat resultat) {
        Athlete athlete = resultat.getAthlete();

        return new ResultatResponse(
                resultat.getId(),
                resultat.getClassement(),
                resultat.getPerformance(),
                resultat.getMedaille(),
                athlete.getId(),
                athlete.getPrenom() + " " + athlete.getNom(),
                athlete.getNationalite(),
                resultat.getEpreuve().getId(),
                resultat.getEpreuve().getNom()
        );
    }

    public void updateEntity(
            Resultat resultat,
            ResultatRequest request,
            Athlete athlete,
            Epreuve epreuve
    ) {
        resultat.setClassement(request.classement());
        resultat.setPerformance(request.performance());
        resultat.setMedaille(calculerMedaille(request.classement()));
        resultat.setAthlete(athlete);
        resultat.setEpreuve(epreuve);
    }

    private Medaille calculerMedaille(Integer classement) {
        return switch (classement) {
            case 1 -> Medaille.OR;
            case 2 -> Medaille.ARGENT;
            case 3 -> Medaille.BRONZE;
            default -> Medaille.AUCUNE;
        };
    }
}