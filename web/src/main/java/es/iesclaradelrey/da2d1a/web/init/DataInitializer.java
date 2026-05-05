package es.iesclaradelrey.da2d1a.web.init;

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
public class DataInitializer implements ApplicationRunner {

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
        if (rolService.findById("ADMIN").isEmpty()) {
            rolService.save(new Rol("ADMIN", "Administrador"));
        }
        if (rolService.findById("USER").isEmpty()) {
            rolService.save(new Rol("USER", "Usuario normal"));
        }
    }

    private void crearAdminSiNoExiste() {
        if (usuarioService.existsByEmail("admin@tienda.com")) return;

        UsuarioRegistrado admin = new UsuarioRegistrado();
        admin.setNombre("Admin");
        admin.setApellidos("Sistema");
        admin.setEmail("admin@tienda.com");
        admin.setContrasena(passwordEncoder.encode("Password"));
        admin.setFechaRegistro(LocalDateTime.now());

        Optional<Rol> rolAdmin = rolService.findById("ADMIN");
        Optional<Rol> rolUser  = rolService.findById("USER");
        rolAdmin.ifPresent(r -> admin.getRoles().add(r));
        rolUser.ifPresent(r  -> admin.getRoles().add(r));

        usuarioService.save(admin);
        System.out.println(">>> Usuario admin@tienda.com creado con roles ADMIN y USER");
    }

    private void crearUserSiNoExiste() {
        if (usuarioService.existsByEmail("user@tienda.com")) return;

        UsuarioRegistrado user = new UsuarioRegistrado();
        user.setNombre("Usuario");
        user.setApellidos("Normal");
        user.setEmail("user@tienda.com");
        user.setContrasena(passwordEncoder.encode("Password"));
        user.setFechaRegistro(LocalDateTime.now());

        Optional<Rol> rolUser = rolService.findById("USER");
        rolUser.ifPresent(r -> user.getRoles().add(r));

        usuarioService.save(user);
        System.out.println(">>> Usuario user@tienda.com creado con rol USER");
    }
}
