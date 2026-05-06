package es.iesclaradelrey.da2d1a.web.controllers;

import es.iesclaradelrey.da2d1a.common.entities.UsuarioRegistrado;
import es.iesclaradelrey.da2d1a.common.services.IUsuarioRegistradoService;
import es.iesclaradelrey.da2d1a.security.userdetails.UsuarioDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
public class UsuarioController {

    @Autowired
    private IUsuarioRegistradoService usuarioService;

    @GetMapping("/login")
    public String loginPage(@RequestParam(required = false) String error,
                            @RequestParam(required = false) String registered,
                            Model model) {
        if (error != null) {
            model.addAttribute("error", "Usuario y/o contraseña incorrectos");
        }
        if (registered != null) {
            model.addAttribute("registrado", "Cuenta creada con éxito. Ya puedes iniciar sesión.");
        }
        return "login";
    }

    @GetMapping("/users/profile")
    public String miPerfil(Authentication authentication, Model model) {
        UsuarioDetails principal = (UsuarioDetails) authentication.getPrincipal();
        UsuarioRegistrado usuario = usuarioService.findById(principal.getId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        model.addAttribute("usuario", usuario);
        return "users/perfil";
    }

    @GetMapping("/users/profile/{userId}")
    @PreAuthorize("hasRole('ADMIN') or principal.id == #userId")
    public String perfilPorId(@PathVariable Long userId, Model model) {
        UsuarioRegistrado usuario = usuarioService.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        model.addAttribute("usuario", usuario);
        return "users/perfil";
    }
}
