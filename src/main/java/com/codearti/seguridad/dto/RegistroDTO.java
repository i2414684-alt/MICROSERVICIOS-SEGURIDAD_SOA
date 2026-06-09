package com.codearti.seguridad.dto;

import jakarta.validation.constraints.*;

public record RegistroDTO(
        @NotBlank(message = "El nombre de usuario es obligatorio")
        String nombreUsuario,

        @Email(message = "El correo no es válido")
        @NotBlank(message = "El correo es obligatorio")
        String correo,

        @NotBlank(message = "La contraseña es obligatoria")
        @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
        String contrasena
) {}