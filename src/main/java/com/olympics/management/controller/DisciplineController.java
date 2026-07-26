package com.olympics.management.controller;

import com.olympics.management.dto.DisciplineRequest;
import com.olympics.management.dto.DisciplineResponse;
import com.olympics.management.service.DisciplineService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disciplines")
public class DisciplineController {

    private final DisciplineService disciplineService;

    public DisciplineController(DisciplineService disciplineService) {
        this.disciplineService = disciplineService;
    }

    @PostMapping
    public ResponseEntity<DisciplineResponse> creer(
            @Valid @RequestBody DisciplineRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(disciplineService.creer(request));
    }

    @GetMapping
    public ResponseEntity<List<DisciplineResponse>> obtenirToutes() {
        return ResponseEntity.ok(disciplineService.obtenirToutes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisciplineResponse> obtenirParId(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(disciplineService.obtenirParId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DisciplineResponse> modifier(
            @PathVariable Long id,
            @Valid @RequestBody DisciplineRequest request
    ) {
        return ResponseEntity.ok(
                disciplineService.modifier(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(
            @PathVariable Long id
    ) {
        disciplineService.supprimer(id);
        return ResponseEntity.noContent().build();
    }
}