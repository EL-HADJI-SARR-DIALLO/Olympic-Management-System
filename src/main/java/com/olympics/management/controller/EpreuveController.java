package com.olympics.management.controller;

import com.olympics.management.dto.EpreuveRequest;
import com.olympics.management.dto.EpreuveResponse;
import com.olympics.management.service.EpreuveService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/epreuves")
public class EpreuveController {

    private final EpreuveService epreuveService;

    public EpreuveController(EpreuveService epreuveService) {
        this.epreuveService = epreuveService;
    }

    @PostMapping
    public ResponseEntity<EpreuveResponse> creer(
            @Valid @RequestBody EpreuveRequest request
    ) {
        EpreuveResponse response = epreuveService.creer(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<EpreuveResponse>> obtenirToutes() {
        return ResponseEntity.ok(
                epreuveService.obtenirToutes()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<EpreuveResponse> obtenirParId(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(
                epreuveService.obtenirParId(id)
        );
    }

    @GetMapping("/discipline/{disciplineId}")
    public ResponseEntity<List<EpreuveResponse>> obtenirParDiscipline(
            @PathVariable Long disciplineId
    ) {
        return ResponseEntity.ok(
                epreuveService.obtenirParDiscipline(disciplineId)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<EpreuveResponse> modifier(
            @PathVariable Long id,
            @Valid @RequestBody EpreuveRequest request
    ) {
        return ResponseEntity.ok(
                epreuveService.modifier(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(
            @PathVariable Long id
    ) {
        epreuveService.supprimer(id);

        return ResponseEntity.noContent().build();
    }
}