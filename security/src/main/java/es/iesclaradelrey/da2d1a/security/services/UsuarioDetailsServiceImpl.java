package es.iesclaradelrey.da2d1a.security.services;

import es.iesclaradelrey.da2d1a.common.entities.UsuarioRegistrado;
import es.iesclaradelrey.da2d1a.common.services.IUsuarioRegistradoService;
import es.iesclaradelrey.da2d1a.security.userdetails.UsuarioDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UsuarioDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IUsuarioRegistradoService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioRegistrado usuario = usuarioService.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "No se encontró el usuario con email: " + username));

        Collection<GrantedAuthority> authorities = usuario.getRoles().stream()
                .map(rol -> new SimpleGrantedAuthority("ROLE_" + rol.getId()))
                .collect(Collectors.toList());

        return new UsuarioDetails(
                usuario.getId(),
                usuario.getEmail(),
                usuario.getContrasena(),
                authorities
        );
    }
}
