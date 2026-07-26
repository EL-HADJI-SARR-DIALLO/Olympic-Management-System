package com.olympics.management.mapper;

import com.olympics.management.dto.AthleteRequest;
import com.olympics.management.dto.AthleteResponse;
import com.olympics.management.entity.Athlete;
import com.olympics.management.entity.Discipline;
import org.springframework.stereotype.Component;

@Component
public class AthleteMapper {

    public Athlete toEntity(
            AthleteRequest request,
            Discipline discipline
    ) {
        Athlete athlete = new Athlete();

        athlete.setNom(request.nom());
        athlete.setPrenom(request.prenom());
        athlete.setDateNaissance(request.dateNaissance());
        athlete.setSexe(request.sexe());
        athlete.setNationalite(request.nationalite());
        athlete.setEmail(request.email());
        athlete.setDiscipline(discipline);

        return athlete;
    }

    public AthleteResponse toResponse(Athlete athlete) {
        return new AthleteResponse(
                athlete.getId(),
                athlete.getNom(),
                athlete.getPrenom(),
                athlete.getDateNaissance(),
                athlete.getSexe(),
                athlete.getNationalite(),
                athlete.getEmail(),
                athlete.getDiscipline().getId(),
                athlete.getDiscipline().getNom()
        );
    }

    public void updateEntity(
            Athlete athlete,
            AthleteRequest request,
            Discipline discipline
    ) {
        athlete.setNom(request.nom());
        athlete.setPrenom(request.prenom());
        athlete.setDateNaissance(request.dateNaissance());
        athlete.setSexe(request.sexe());
        athlete.setNationalite(request.nationalite());
        athlete.setEmail(request.email());
        athlete.setDiscipline(discipline);
    }
}