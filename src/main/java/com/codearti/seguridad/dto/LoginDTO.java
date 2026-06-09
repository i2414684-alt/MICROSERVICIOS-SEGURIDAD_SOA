package com.codearti.seguridad.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginDTO(
        @NotBlank String nombreUsuario,
        @NotBlank String contrasena
) {}