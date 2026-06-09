package com.codearti.seguridad.servicio;

import com.codearti.seguridad.dto.AuthRespuestaDTO;
import com.codearti.seguridad.dto.LoginDTO;
import com.codearti.seguridad.dto.RegistroDTO;
import com.codearti.seguridad.entidad.Rol;
import com.codearti.seguridad.entidad.Usuario;
import com.codearti.seguridad.repositorio.UsuarioRepositorio;
import com.codearti.seguridad.seguridad.JwtServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AutenticacionServicio {

    private final UsuarioRepositorio usuarioRepositorio;
    private final PasswordEncoder codificadorContrasena;
    private final JwtServicio jwtServicio;
    private final AuthenticationManager gestorAutenticacion;
    private final UsuarioDetallesServicio usuarioDetallesServicio;

    public AuthRespuestaDTO registrar(RegistroDTO dto) {
        if (usuarioRepositorio.existsByNombreUsuario(dto.nombreUsuario())) {
            throw new IllegalArgumentException("El nombre de usuario ya está en uso");
        }
        if (usuarioRepositorio.existsByCorreo(dto.correo())) {
            throw new IllegalArgumentException("El correo ya está registrado");
        }

        Usuario usuario = Usuario.builder()
                .nombreUsuario(dto.nombreUsuario())
                .correo(dto.correo())
                .contrasena(codificadorContrasena.encode(dto.contrasena()))
                .rol(Rol.USUARIO)
                .build();

        usuarioRepositorio.save(usuario);

        UserDetails detalles =
                usuarioDetallesServicio.loadUserByUsername(usuario.getNombreUsuario());
        String token = jwtServicio.generarToken(detalles);
        return new AuthRespuestaDTO(token, "Bearer");
    }

    public AuthRespuestaDTO iniciarSesion(LoginDTO dto) {
        gestorAutenticacion.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.nombreUsuario(), dto.contrasena()));

        UserDetails detalles =
                usuarioDetallesServicio.loadUserByUsername(dto.nombreUsuario());
        String token = jwtServicio.generarToken(detalles);
        return new AuthRespuestaDTO(token, "Bearer");
    }
}