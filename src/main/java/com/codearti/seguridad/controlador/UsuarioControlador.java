package com.codearti.seguridad.controlador;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioControlador {

    @GetMapping("/perfil")
    public String perfil(Authentication auth) {
        return "Hola " + auth.getName()
                + ", accediste correctamente a un recurso protegido.";
    }
}