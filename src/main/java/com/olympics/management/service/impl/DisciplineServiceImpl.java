package com.olympics.management.service.impl;

import com.olympics.management.dto.DisciplineRequest;
import com.olympics.management.dto.DisciplineResponse;
import com.olympics.management.entity.Discipline;
import com.olympics.management.exception.DuplicateResourceException;
import com.olympics.management.exception.ResourceNotFoundException;
import com.olympics.management.mapper.DisciplineMapper;
import com.olympics.management.repository.DisciplineRepository;
import com.olympics.management.service.DisciplineService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplineServiceImpl implements DisciplineService {

    private final DisciplineRepository disciplineRepository;
    private final DisciplineMapper disciplineMapper;

    public DisciplineServiceImpl(
            DisciplineRepository disciplineRepository,
            DisciplineMapper disciplineMapper
    ) {
        this.disciplineRepository = disciplineRepository;
        this.disciplineMapper = disciplineMapper;
    }

    @Override
    public DisciplineResponse creer(DisciplineRequest request) {
        verifierNomDisponible(request.nom());

        Discipline discipline = disciplineMapper.toEntity(request);
        Discipline disciplineEnregistree =
                disciplineRepository.save(discipline);

        return disciplineMapper.toResponse(disciplineEnregistree);
    }

    @Override
    public List<DisciplineResponse> obtenirToutes() {
        return disciplineRepository.findAll()
                .stream()
                .map(disciplineMapper::toResponse)
                .toList();
    }

    @Override
    public DisciplineResponse obtenirParId(Long id) {
        Discipline discipline = trouverParId(id);

        return disciplineMapper.toResponse(discipline);
    }

    @Override
    public DisciplineResponse modifier(
            Long id,
            DisciplineRequest request
    ) {
        Discipline discipline = trouverParId(id);

        verifierNomDisponiblePourModification(
                request.nom(),
                discipline.getId()
        );

        disciplineMapper.updateEntity(discipline, request);

        Discipline disciplineModifiee =
                disciplineRepository.save(discipline);

        return disciplineMapper.toResponse(disciplineModifiee);
    }

    @Override
    public void supprimer(Long id) {
        Discipline discipline = trouverParId(id);
        disciplineRepository.delete(discipline);
    }

    private Discipline trouverParId(Long id) {
        return disciplineRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Discipline introuvable avec l'identifiant : "
                                        + id
                        )
                );
    }

    private void verifierNomDisponible(String nom) {
        if (disciplineRepository.existsByNomIgnoreCase(nom)) {
            throw new DuplicateResourceException(
                    "Une discipline portant ce nom existe déjà"
            );
        }
    }

    private void verifierNomDisponiblePourModification(
            String nom,
            Long disciplineId
    ) {
        disciplineRepository.findByNomIgnoreCase(nom)
                .filter(discipline ->
                        !discipline.getId().equals(disciplineId)
                )
                .ifPresent(discipline -> {
                    throw new DuplicateResourceException(
                            "Une autre discipline portant ce nom existe déjà"
                    );
                });
    }
}