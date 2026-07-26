package com.olympics.management.service.impl;

import com.olympics.management.dto.ResultatRequest;
import com.olympics.management.dto.ResultatResponse;
import com.olympics.management.entity.Athlete;
import com.olympics.management.entity.Epreuve;
import com.olympics.management.entity.Resultat;
import com.olympics.management.exception.DuplicateResourceException;
import com.olympics.management.exception.ResourceNotFoundException;
import com.olympics.management.mapper.ResultatMapper;
import com.olympics.management.repository.AthleteRepository;
import com.olympics.management.repository.EpreuveRepository;
import com.olympics.management.repository.ResultatRepository;
import com.olympics.management.service.ResultatService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.olympics.management.exception.BusinessRuleException;

import java.util.List;

@Service
@Transactional
public class ResultatServiceImpl implements ResultatService {

    private final ResultatRepository resultatRepository;
    private final AthleteRepository athleteRepository;
    private final EpreuveRepository epreuveRepository;
    private final ResultatMapper resultatMapper;

    public ResultatServiceImpl(
            ResultatRepository resultatRepository,
            AthleteRepository athleteRepository,
            EpreuveRepository epreuveRepository,
            ResultatMapper resultatMapper
    ) {
        this.resultatRepository = resultatRepository;
        this.athleteRepository = athleteRepository;
        this.epreuveRepository = epreuveRepository;
        this.resultatMapper = resultatMapper;
    }

    @Override
    public ResultatResponse creer(ResultatRequest request) {

        Athlete athlete = trouverAthlete(request.athleteId());
        Epreuve epreuve = trouverEpreuve(request.epreuveId());

        verifierCompatibiliteDisciplines(athlete, epreuve);

        verifierDoublon(
                athlete.getId(),
                epreuve.getId()
        );

        Resultat resultat = resultatMapper.toEntity(
                request,
                athlete,
                epreuve
        );

        Resultat resultatEnregistre =
                resultatRepository.save(resultat);

        return resultatMapper.toResponse(resultatEnregistre);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResultatResponse> obtenirTous() {
        return resultatRepository.findAll()
                .stream()
                .map(resultatMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ResultatResponse obtenirParId(Long id) {
        return resultatMapper.toResponse(
                trouverResultat(id)
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResultatResponse> obtenirParAthlete(Long athleteId) {

        trouverAthlete(athleteId);

        return resultatRepository.findByAthleteId(athleteId)
                .stream()
                .map(resultatMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResultatResponse> obtenirParEpreuve(Long epreuveId) {

        trouverEpreuve(epreuveId);

        return resultatRepository
                .findByEpreuveIdOrderByClassementAsc(epreuveId)
                .stream()
                .map(resultatMapper::toResponse)
                .toList();
    }

    @Override
    public ResultatResponse modifier(
            Long id,
            ResultatRequest request
    ) {

        Resultat resultat = trouverResultat(id);

        Athlete athlete = trouverAthlete(request.athleteId());
        Epreuve epreuve = trouverEpreuve(request.epreuveId());

        verifierCompatibiliteDisciplines(athlete, epreuve);

        verifierDoublonModification(
                athlete.getId(),
                epreuve.getId(),
                resultat.getId()
        );

        resultatMapper.updateEntity(
                resultat,
                request,
                athlete,
                epreuve
        );

        Resultat resultatModifie =
                resultatRepository.save(resultat);

        return resultatMapper.toResponse(resultatModifie);
    }

    @Override
    public void supprimer(Long id) {
        Resultat resultat = trouverResultat(id);
        resultatRepository.delete(resultat);
    }

    private Resultat trouverResultat(Long id) {
        return resultatRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Résultat introuvable avec l'identifiant : " + id
                        )
                );
    }

    private Athlete trouverAthlete(Long id) {
        return athleteRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Athlète introuvable avec l'identifiant : " + id
                        )
                );
    }

    private Epreuve trouverEpreuve(Long id) {
        return epreuveRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Épreuve introuvable avec l'identifiant : " + id
                        )
                );
    }

    private void verifierCompatibiliteDisciplines(
            Athlete athlete,
            Epreuve epreuve
    ) {
        Long disciplineAthleteId =
                athlete.getDiscipline().getId();

        Long disciplineEpreuveId =
                epreuve.getDiscipline().getId();

        if (!disciplineAthleteId.equals(disciplineEpreuveId)) {
            throw new IllegalArgumentException(
                    "L'athlète et l'épreuve doivent appartenir à la même discipline"
            );
        }
    }

    private void verifierDoublon(
            Long athleteId,
            Long epreuveId
    ) {
        if (resultatRepository.existsByAthleteIdAndEpreuveId(
                athleteId,
                epreuveId
        )) {
            throw new DuplicateResourceException(
                    "Un résultat existe déjà pour cet athlète dans cette épreuve"
            );
        }
    }

    private void verifierDoublonModification(
            Long athleteId,
            Long epreuveId,
            Long resultatId
    ) {
        resultatRepository
                .findByAthleteIdAndEpreuveId(
                        athleteId,
                        epreuveId
                )
                .filter(resultat ->
                        !resultat.getId().equals(resultatId)
                )
                .ifPresent(resultat -> {
                    throw new BusinessRuleException(
                            "L'athlète et l'épreuve doivent appartenir à la même discipline"
                    );
                });
    }
}