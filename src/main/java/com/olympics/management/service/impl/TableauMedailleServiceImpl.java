package com.olympics.management.service.impl;

import com.olympics.management.dto.TableauMedailleProjection;
import com.olympics.management.dto.TableauMedailleResponse;
import com.olympics.management.repository.ResultatRepository;
import com.olympics.management.service.TableauMedailleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class TableauMedailleServiceImpl
        implements TableauMedailleService {

    private final ResultatRepository resultatRepository;

    public TableauMedailleServiceImpl(
            ResultatRepository resultatRepository
    ) {
        this.resultatRepository = resultatRepository;
    }

    @Override
    public List<TableauMedailleResponse> obtenirTableauMedailles() {

        List<TableauMedailleProjection> projections =
                resultatRepository.calculerTableauMedailles();

        List<TableauMedailleResponse> tableau = new ArrayList<>();

        for (int index = 0; index < projections.size(); index++) {

            TableauMedailleProjection projection =
                    projections.get(index);

            tableau.add(
                    new TableauMedailleResponse(
                            index + 1,
                            projection.getNationalite(),
                            projection.getNombreOr(),
                            projection.getNombreArgent(),
                            projection.getNombreBronze(),
                            projection.getTotal()
                    )
            );
        }

        return tableau;
    }
}