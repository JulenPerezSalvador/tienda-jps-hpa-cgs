package es.iesclaradelrey.da2d1a.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioController {
    // es como si fuera una doble ruta por si se quita la barra del final
    @GetMapping({"/", ""})
    public String index() {
        return "index";
    }
}