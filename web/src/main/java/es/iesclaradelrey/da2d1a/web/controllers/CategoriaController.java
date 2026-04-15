package es.iesclaradelrey.da2d1a.web.controllers;

import es.iesclaradelrey.da2d1a.common.services.ICategoriaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    private final ICategoriaService categoriaService;

    public CategoriaController(ICategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping({"/", ""})
    public String listar(Model model) {
        model.addAttribute("categorias", categoriaService.findAll());
        return "categorias-listado";
    }

    @GetMapping("/{id}")
    public String detalle(@PathVariable Long id, Model model) {
        categoriaService.findById(id).ifPresent(c -> model.addAttribute("categoria", c));
        return "categoria-detalle";
    }
}