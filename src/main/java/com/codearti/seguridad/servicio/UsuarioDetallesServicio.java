package com.codearti.seguridad.servicio;

import com.codearti.seguridad.repositorio.UsuarioRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioDetallesServicio implements UserDetailsService {

    private final UsuarioRepositorio usuarioRepositorio;

    @Override
    public UserDetails loadUserByUsername(String nombreUsuario)
            throws UsernameNotFoundException {

        var usuario = usuarioRepositorio.findByNombreUsuario(nombreUsuario)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "Usuario no encontrado: " + nombreUsuario));

        return User.builder()
                .username(usuario.getNombreUsuario())
                .password(usuario.getContrasena())
                .authorities(List.of(
                        new SimpleGrantedAuthority("ROL_" + usuario.getRol().name())))
                .build();
    }
}