package com.olympics.management.mapper;

import com.olympics.management.dto.DisciplineRequest;
import com.olympics.management.dto.DisciplineResponse;
import com.olympics.management.entity.Discipline;
import org.springframework.stereotype.Component;

@Component
public class DisciplineMapper {

    public Discipline toEntity(DisciplineRequest request) {
        Discipline discipline = new Discipline();

        discipline.setNom(request.nom());
        discipline.setDescription(request.description());

        return discipline;
    }

    public DisciplineResponse toResponse(Discipline discipline) {
        return new DisciplineResponse(
                discipline.getId(),
                discipline.getNom(),
                discipline.getDescription()
        );
    }

    public void updateEntity(
            Discipline discipline,
            DisciplineRequest request
    ) {
        discipline.setNom(request.nom());
        discipline.setDescription(request.description());
    }
}