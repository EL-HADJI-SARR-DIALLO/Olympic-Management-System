package com.olympics.management.service;

import com.olympics.management.dto.TableauMedailleResponse;

import java.util.List;

public interface TableauMedailleService {

    List<TableauMedailleResponse> obtenirTableauMedailles();
}