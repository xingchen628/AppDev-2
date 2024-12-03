package com.example.groupproject.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record PetDTO(
    @NotBlank String name,
    @NotBlank String animalType,
    @NotBlank String breed,
    @Min(0) @Max(20) int age,
    @NotBlank String householdEircode
) {}
