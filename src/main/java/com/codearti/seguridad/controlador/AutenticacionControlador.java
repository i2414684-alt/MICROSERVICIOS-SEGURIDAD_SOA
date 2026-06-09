package com.codearti.seguridad.controlador;

import com.codearti.seguridad.dto.AuthRespuestaDTO;
import com.codearti.seguridad.dto.LoginDTO;
import com.codearti.seguridad.dto.RegistroDTO;
import com.codearti.seguridad.servicio.AutenticacionServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AutenticacionControlador {

    private final AutenticacionServicio autenticacionServicio;

    @PostMapping("/registro")
    public ResponseEntity<AuthRespuestaDTO> registrar(
            @Valid @RequestBody RegistroDTO dto) {
        return ResponseEntity.ok(autenticacionServicio.registrar(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthRespuestaDTO> login(
            @Valid @RequestBody LoginDTO dto) {
        return ResponseEntity.ok(autenticacionServicio.iniciarSesion(dto));
    }
}