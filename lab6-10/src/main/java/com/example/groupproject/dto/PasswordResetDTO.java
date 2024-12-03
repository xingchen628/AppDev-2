package com.example.groupproject.dto;

import jakarta.validation.constraints.NotBlank;

public record PasswordResetDTO(
    @NotBlank String newPassword
) {}
