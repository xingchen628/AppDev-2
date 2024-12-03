package com.example.groupproject.dto;

import jakarta.validation.constraints.NotBlank;

public record ChangePetNameDTO(
    @NotBlank String name
) {}
