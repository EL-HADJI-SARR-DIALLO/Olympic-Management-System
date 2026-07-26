package com.olympics.management.service;

import com.olympics.management.dto.AthleteRequest;
import com.olympics.management.dto.AthleteResponse;

import java.util.List;

public interface AthleteService {

    AthleteResponse creer(AthleteRequest request);

    List<AthleteResponse> obtenirTous();

    AthleteResponse obtenirParId(Long id);

    AthleteResponse modifier(Long id, AthleteRequest request);

    void supprimer(Long id);
}