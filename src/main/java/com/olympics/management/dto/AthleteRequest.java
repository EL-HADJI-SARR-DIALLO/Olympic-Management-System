package com.olympics.management.dto;

import com.olympics.management.enums.Sexe;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record AthleteRequest(

        @NotBlank(message = "Le nom est obligatoire")
        @Size(max = 100, message = "Le nom ne doit pas dépasser 100 caractères")
        String nom,

        @NotBlank(message = "Le prénom est obligatoire")
        @Size(max = 100, message = "Le prénom ne doit pas dépasser 100 caractères")
        String prenom,

        @NotNull(message = "La date de naissance est obligatoire")
        @Past(message = "La date de naissance doit être dans le passé")
        LocalDate dateNaissance,

        @NotNull(message = "Le sexe est obligatoire")
        Sexe sexe,

        @NotBlank(message = "La nationalité est obligatoire")
        @Size(max = 100, message = "La nationalité ne doit pas dépasser 100 caractères")
        String nationalite,

        @NotBlank(message = "L'adresse email est obligatoire")
        @Email(message = "L'adresse email n'est pas valide")
        @Size(max = 150, message = "L'adresse email ne doit pas dépasser 150 caractères")
        String email,

        @NotNull(message = "L'identifiant de la discipline est obligatoire")
        @Positive(message = "L'identifiant de la discipline doit être positif")
        Long disciplineId
) {
}