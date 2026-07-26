package com.olympics.management.service.impl;

import com.olympics.management.dto.AthleteRequest;
import com.olympics.management.dto.AthleteResponse;
import com.olympics.management.entity.Athlete;
import com.olympics.management.entity.Discipline;
import com.olympics.management.exception.DuplicateResourceException;
import com.olympics.management.exception.ResourceNotFoundException;
import com.olympics.management.mapper.AthleteMapper;
import com.olympics.management.repository.AthleteRepository;
import com.olympics.management.repository.DisciplineRepository;
import com.olympics.management.service.AthleteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AthleteServiceImpl implements AthleteService {

    private final AthleteRepository athleteRepository;
    private final DisciplineRepository disciplineRepository;
    private final AthleteMapper athleteMapper;

    public AthleteServiceImpl(
            AthleteRepository athleteRepository,
            DisciplineRepository disciplineRepository,
            AthleteMapper athleteMapper
    ) {
        this.athleteRepository = athleteRepository;
        this.disciplineRepository = disciplineRepository;
        this.athleteMapper = athleteMapper;
    }

    @Override
    public AthleteResponse creer(AthleteRequest request) {
        verifierEmailDisponible(request.email());

        Discipline discipline = trouverDisciplineParId(
                request.disciplineId()
        );

        Athlete athlete = athleteMapper.toEntity(
                request,
                discipline
        );

        Athlete athleteEnregistre = athleteRepository.save(athlete);

        return athleteMapper.toResponse(athleteEnregistre);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AthleteResponse> obtenirTous() {
        return athleteRepository.findAll()
                .stream()
                .map(athleteMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public AthleteResponse obtenirParId(Long id) {
        Athlete athlete = trouverAthleteParId(id);

        return athleteMapper.toResponse(athlete);
    }

    @Override
    public AthleteResponse modifier(
            Long id,
            AthleteRequest request
    ) {
        Athlete athlete = trouverAthleteParId(id);

        verifierEmailDisponiblePourModification(
                request.email(),
                athlete.getId()
        );

        Discipline discipline = trouverDisciplineParId(
                request.disciplineId()
        );

        athleteMapper.updateEntity(
                athlete,
                request,
                discipline
        );

        Athlete athleteModifie = athleteRepository.save(athlete);

        return athleteMapper.toResponse(athleteModifie);
    }

    @Override
    public void supprimer(Long id) {
        Athlete athlete = trouverAthleteParId(id);
        athleteRepository.delete(athlete);
    }

    private Athlete trouverAthleteParId(Long id) {
        return athleteRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Athlète introuvable avec l'identifiant : " + id
                        )
                );
    }

    private Discipline trouverDisciplineParId(Long disciplineId) {
        return disciplineRepository.findById(disciplineId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Discipline introuvable avec l'identifiant : "
                                        + disciplineId
                        )
                );
    }

    private void verifierEmailDisponible(String email) {
        if (athleteRepository.existsByEmailIgnoreCase(email)) {
            throw new DuplicateResourceException(
                    "Un athlète utilisant cette adresse email existe déjà"
            );
        }
    }

    private void verifierEmailDisponiblePourModification(
            String email,
            Long athleteId
    ) {
        athleteRepository.findByEmailIgnoreCase(email)
                .filter(athlete ->
                        !athlete.getId().equals(athleteId)
                )
                .ifPresent(athlete -> {
                    throw new DuplicateResourceException(
                            "Un autre athlète utilise déjà cette adresse email"
                    );
                });
    }
}