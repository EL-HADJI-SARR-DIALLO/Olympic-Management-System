package com.olympics.management.service.impl;

import com.olympics.management.dto.EpreuveRequest;
import com.olympics.management.dto.EpreuveResponse;
import com.olympics.management.entity.Discipline;
import com.olympics.management.entity.Epreuve;
import com.olympics.management.exception.DuplicateResourceException;
import com.olympics.management.exception.ResourceNotFoundException;
import com.olympics.management.mapper.EpreuveMapper;
import com.olympics.management.repository.DisciplineRepository;
import com.olympics.management.repository.EpreuveRepository;
import com.olympics.management.service.EpreuveService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EpreuveServiceImpl implements EpreuveService {

    private final EpreuveRepository epreuveRepository;
    private final DisciplineRepository disciplineRepository;
    private final EpreuveMapper epreuveMapper;

    public EpreuveServiceImpl(
            EpreuveRepository epreuveRepository,
            DisciplineRepository disciplineRepository,
            EpreuveMapper epreuveMapper
    ) {
        this.epreuveRepository = epreuveRepository;
        this.disciplineRepository = disciplineRepository;
        this.epreuveMapper = epreuveMapper;
    }

    @Override
    public EpreuveResponse creer(EpreuveRequest request) {

        Discipline discipline = trouverDiscipline(request.disciplineId());

        verifierDoublon(
                request.nom(),
                discipline.getId()
        );

        Epreuve epreuve = epreuveMapper.toEntity(
                request,
                discipline
        );

        return epreuveMapper.toResponse(
                epreuveRepository.save(epreuve)
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<EpreuveResponse> obtenirToutes() {

        return epreuveRepository.findAll()
                .stream()
                .map(epreuveMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public EpreuveResponse obtenirParId(Long id) {

        return epreuveMapper.toResponse(
                trouverEpreuve(id)
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<EpreuveResponse> obtenirParDiscipline(Long disciplineId) {

        return epreuveRepository.findByDisciplineId(disciplineId)
                .stream()
                .map(epreuveMapper::toResponse)
                .toList();
    }

    @Override
    public EpreuveResponse modifier(
            Long id,
            EpreuveRequest request
    ) {

        Epreuve epreuve = trouverEpreuve(id);

        Discipline discipline = trouverDiscipline(
                request.disciplineId()
        );

        verifierDoublonModification(
                request.nom(),
                discipline.getId(),
                id
        );

        epreuveMapper.updateEntity(
                epreuve,
                request,
                discipline
        );

        return epreuveMapper.toResponse(
                epreuveRepository.save(epreuve)
        );
    }

    @Override
    public void supprimer(Long id) {

        Epreuve epreuve = trouverEpreuve(id);

        epreuveRepository.delete(epreuve);
    }

    private Epreuve trouverEpreuve(Long id) {

        return epreuveRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Épreuve introuvable avec l'identifiant : " + id
                        ));
    }

    private Discipline trouverDiscipline(Long id) {

        return disciplineRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Discipline introuvable avec l'identifiant : " + id
                        ));
    }

    private void verifierDoublon(
            String nom,
            Long disciplineId
    ) {

        if (epreuveRepository.existsByNomIgnoreCaseAndDisciplineId(
                nom,
                disciplineId
        )) {
            throw new DuplicateResourceException(
                    "Une épreuve portant ce nom existe déjà dans cette discipline"
            );
        }
    }

    private void verifierDoublonModification(
            String nom,
            Long disciplineId,
            Long epreuveId
    ) {

        epreuveRepository
                .findByNomIgnoreCaseAndDisciplineId(
                        nom,
                        disciplineId
                )
                .filter(e -> !e.getId().equals(epreuveId))
                .ifPresent(e -> {
                    throw new DuplicateResourceException(
                            "Une autre épreuve porte déjà ce nom dans cette discipline"
                    );
                });
    }
}