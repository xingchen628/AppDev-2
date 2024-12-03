package com.example.groupproject.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record HouseholdDTO(
    @NotBlank String eircode,
    @Min(0) int numberOfOccupants,
    @Min(1) int maxNumberOfOccupants,
    boolean ownerOccupied
) {}