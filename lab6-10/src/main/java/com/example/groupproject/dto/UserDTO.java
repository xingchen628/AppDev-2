package com.example.groupproject.dto;

import jakarta.validation.constraints.NotBlank;

public record UserDTO(
    Long id,
    @NotBlank String username,
    @NotBlank String firstName,
    @NotBlank String lastName,
    @NotBlank String county,
    @NotBlank String role,
    String password
) {}

