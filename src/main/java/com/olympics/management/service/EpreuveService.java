package com.olympics.management.service;

import com.olympics.management.dto.EpreuveRequest;
import com.olympics.management.dto.EpreuveResponse;

import java.util.List;

public interface EpreuveService {

    EpreuveResponse creer(EpreuveRequest request);

    List<EpreuveResponse> obtenirToutes();

    EpreuveResponse obtenirParId(Long id);

    List<EpreuveResponse> obtenirParDiscipline(Long disciplineId);

    EpreuveResponse modifier(Long id, EpreuveRequest request);

    void supprimer(Long id);
}