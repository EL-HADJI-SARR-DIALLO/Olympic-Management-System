package com.olympics.management.service;

import com.olympics.management.dto.ResultatRequest;
import com.olympics.management.dto.ResultatResponse;

import java.util.List;

public interface ResultatService {

    ResultatResponse creer(ResultatRequest request);

    List<ResultatResponse> obtenirTous();

    ResultatResponse obtenirParId(Long id);

    List<ResultatResponse> obtenirParAthlete(Long athleteId);

    List<ResultatResponse> obtenirParEpreuve(Long epreuveId);

    ResultatResponse modifier(Long id, ResultatRequest request);

    void supprimer(Long id);
}