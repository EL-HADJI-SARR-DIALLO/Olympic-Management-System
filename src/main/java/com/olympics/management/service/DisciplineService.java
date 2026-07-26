package com.olympics.management.service;

import com.olympics.management.dto.DisciplineRequest;
import com.olympics.management.dto.DisciplineResponse;

import java.util.List;

public interface DisciplineService {

    DisciplineResponse creer(DisciplineRequest request);

    List<DisciplineResponse> obtenirToutes();

    DisciplineResponse obtenirParId(Long id);

    DisciplineResponse modifier(Long id, DisciplineRequest request);

    void supprimer(Long id);
}