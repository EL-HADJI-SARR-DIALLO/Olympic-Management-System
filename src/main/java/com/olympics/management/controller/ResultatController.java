package com.olympics.management.controller;

import com.olympics.management.dto.ResultatRequest;
import com.olympics.management.dto.ResultatResponse;
import com.olympics.management.service.ResultatService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resultats")
public class ResultatController {

    private final ResultatService resultatService;

    public ResultatController(ResultatService resultatService) {
        this.resultatService = resultatService;
    }

    @PostMapping
    public ResponseEntity<ResultatResponse> creer(
            @Valid @RequestBody ResultatRequest request
    ) {
        ResultatResponse response = resultatService.creer(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<ResultatResponse>> obtenirTous() {
        return ResponseEntity.ok(
                resultatService.obtenirTous()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResultatResponse> obtenirParId(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(
                resultatService.obtenirParId(id)
        );
    }

    @GetMapping("/athlete/{athleteId}")
    public ResponseEntity<List<ResultatResponse>> obtenirParAthlete(
            @PathVariable Long athleteId
    ) {
        return ResponseEntity.ok(
                resultatService.obtenirParAthlete(athleteId)
        );
    }

    @GetMapping("/epreuve/{epreuveId}")
    public ResponseEntity<List<ResultatResponse>> obtenirParEpreuve(
            @PathVariable Long epreuveId
    ) {
        return ResponseEntity.ok(
                resultatService.obtenirParEpreuve(epreuveId)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResultatResponse> modifier(
            @PathVariable Long id,
            @Valid @RequestBody ResultatRequest request
    ) {
        return ResponseEntity.ok(
                resultatService.modifier(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(
            @PathVariable Long id
    ) {
        resultatService.supprimer(id);

        return ResponseEntity.noContent().build();
    }
}