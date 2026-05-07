package es.iesclaradelrey.da2d1a.api.init;

import es.iesclaradelrey.da2d1a.common.entities.Rol;
import es.iesclaradelrey.da2d1a.common.entities.UsuarioRegistrado;
import es.iesclaradelrey.da2d1a.common.services.IRolService;
import es.iesclaradelrey.da2d1a.common.services.IUsuarioRegistradoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class ApiDataInitializer implements ApplicationRunner {

    @Autowired private IUsuarioRegistradoService usuarioService;
    @Autowired private IRolService rolService;
    @Autowired private PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) {
        crearRolesSiNoExisten();
        crearAdminSiNoExiste();
        crearUserSiNoExiste();
    }

    private void crearRolesSiNoExisten() {
        if (rolService.findById("ADMIN").isEmpty())
            rolService.save(new Rol("ADMIN", "Administrador"));
        if (rolService.findById("USER").isEmpty())
            rolService.save(new Rol("USER", "Usuario normal"));
    }

    private void crearAdminSiNoExiste() {
        if (usuarioService.existsByEmail("admin@tienda.com")) return;
        UsuarioRegistrado admin = new UsuarioRegistrado();
        admin.setNombre("Admin"); admin.setApellidos("Sistema");
        admin.setEmail("admin@tienda.com");
        admin.setContrasena(passwordEncoder.encode("Password"));
        admin.setFechaRegistro(LocalDateTime.now());
        rolService.findById("ADMIN").ifPresent(r -> admin.getRoles().add(r));
        rolService.findById("USER").ifPresent(r -> admin.getRoles().add(r));
        usuarioService.save(admin);
    }

    private void crearUserSiNoExiste() {
        if (usuarioService.existsByEmail("user@tienda.com")) return;
        UsuarioRegistrado user = new UsuarioRegistrado();
        user.setNombre("Usuario"); user.setApellidos("Normal");
        user.setEmail("user@tienda.com");
        user.setContrasena(passwordEncoder.encode("Password"));
        user.setFechaRegistro(LocalDateTime.now());
        rolService.findById("USER").ifPresent(r -> user.getRoles().add(r));
        usuarioService.save(user);
    }
}
