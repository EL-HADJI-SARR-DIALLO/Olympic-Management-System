package com.olympics.management.controller;

import com.olympics.management.dto.TableauMedailleResponse;
import com.olympics.management.service.TableauMedailleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tableau-medailles")
public class TableauMedailleController {

    private final TableauMedailleService tableauMedailleService;

    public TableauMedailleController(
            TableauMedailleService tableauMedailleService
    ) {
        this.tableauMedailleService = tableauMedailleService;
    }

    @GetMapping
    public ResponseEntity<List<TableauMedailleResponse>>
    obtenirTableauMedailles() {

        return ResponseEntity.ok(
                tableauMedailleService.obtenirTableauMedailles()
        );
    }
}