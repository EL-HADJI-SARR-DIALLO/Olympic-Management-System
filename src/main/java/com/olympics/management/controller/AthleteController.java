package com.olympics.management.controller;

import com.olympics.management.dto.AthleteRequest;
import com.olympics.management.dto.AthleteResponse;
import com.olympics.management.service.AthleteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/athletes")
public class AthleteController {

    private final AthleteService athleteService;

    public AthleteController(AthleteService athleteService) {
        this.athleteService = athleteService;
    }

    @PostMapping
    public ResponseEntity<AthleteResponse> creer(
            @Valid @RequestBody AthleteRequest request
    ) {
        AthleteResponse response = athleteService.creer(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<AthleteResponse>> obtenirTous() {
        return ResponseEntity.ok(athleteService.obtenirTous());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AthleteResponse> obtenirParId(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(
                athleteService.obtenirParId(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<AthleteResponse> modifier(
            @PathVariable Long id,
            @Valid @RequestBody AthleteRequest request
    ) {
        return ResponseEntity.ok(
                athleteService.modifier(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(
            @PathVariable Long id
    ) {
        athleteService.supprimer(id);

        return ResponseEntity.noContent().build();
    }
}