package com.olympics.management.mapper;

import com.olympics.management.dto.EpreuveRequest;
import com.olympics.management.dto.EpreuveResponse;
import com.olympics.management.entity.Discipline;
import com.olympics.management.entity.Epreuve;
import org.springframework.stereotype.Component;

@Component
public class EpreuveMapper {

    public Epreuve toEntity(
            EpreuveRequest request,
            Discipline discipline
    ) {
        Epreuve epreuve = new Epreuve();

        epreuve.setNom(request.nom());
        epreuve.setDescription(request.description());
        epreuve.setDateHeure(request.dateHeure());
        epreuve.setLieu(request.lieu());
        epreuve.setDiscipline(discipline);

        return epreuve;
    }

    public EpreuveResponse toResponse(Epreuve epreuve) {
        return new EpreuveResponse(
                epreuve.getId(),
                epreuve.getNom(),
                epreuve.getDescription(),
                epreuve.getDateHeure(),
                epreuve.getLieu(),
                epreuve.getDiscipline().getId(),
                epreuve.getDiscipline().getNom()
        );
    }

    public void updateEntity(
            Epreuve epreuve,
            EpreuveRequest request,
            Discipline discipline
    ) {
        epreuve.setNom(request.nom());
        epreuve.setDescription(request.description());
        epreuve.setDateHeure(request.dateHeure());
        epreuve.setLieu(request.lieu());
        epreuve.setDiscipline(discipline);
    }
}