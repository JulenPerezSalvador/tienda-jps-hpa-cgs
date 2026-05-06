package es.iesclaradelrey.da2d1a.web.controllers;

import es.iesclaradelrey.da2d1a.common.entities.Rol;
import es.iesclaradelrey.da2d1a.common.entities.UsuarioRegistrado;
import es.iesclaradelrey.da2d1a.common.services.IRolService;
import es.iesclaradelrey.da2d1a.common.services.IUsuarioRegistradoService;
import es.iesclaradelrey.da2d1a.web.dto.UsuarioRegistroDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/register")
public class RegistroController {

    @Autowired
    private IUsuarioRegistradoService usuarioService;

    @Autowired
    private IRolService rolService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public String mostrarFormulario(Model model) {
        model.addAttribute("usuarioDTO", new UsuarioRegistroDTO());
        return "register";
    }
    @PostMapping
    public String procesarRegistro(@Valid @ModelAttribute("usuarioDTO") UsuarioRegistroDTO dto,
                                   BindingResult result,
                                   Model model) {
        if (result.hasErrors()) {
            return "register";
        }

        if (usuarioService.existsByEmail(dto.getEmail())) {
            result.rejectValue("email", "email.duplicado",
                    "Ya existe una cuenta registrada con ese correo electrónico");
            return "register";
        }

        try {
            UsuarioRegistrado nuevo = new UsuarioRegistrado();
            nuevo.setNombre(dto.getNombre());
            nuevo.setApellidos(dto.getApellidos());
            nuevo.setEmail(dto.getEmail());
            nuevo.setTelefono(dto.getTelefono());
            nuevo.setFechaNacimiento(dto.getFechaNacimiento());
            nuevo.setContrasena(passwordEncoder.encode(dto.getContrasena()));
            nuevo.setFechaRegistro(LocalDateTime.now());
            Optional<Rol> rolUser = rolService.findById("USER");
            rolUser.ifPresent(rol -> nuevo.getRoles().add(rol));

            usuarioService.save(nuevo);
        } catch (Exception e) {
            model.addAttribute("errorGlobal",
                    "Error al crear la cuenta: " + e.getMessage());
            return "register";
        }

        return "redirect:/login?registered";
    }
}
