package es.iesclaradelrey.da2d1a.web.controllers;

import es.iesclaradelrey.da2d1a.common.entities.Categoria;
import es.iesclaradelrey.da2d1a.common.services.ICategoriaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/categorias")
public class CategoriaAdminController {

    private final ICategoriaService categoriaService;

    public CategoriaAdminController(ICategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping("")
    public String listado(Model model) {
        model.addAttribute("categorias", categoriaService.findAll());
        return "admin/categorias-listado-admin";
    }

    @GetMapping("/crear")
    public String mostrarCrear(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "admin/categorias-form-admin";
    }

    @GetMapping("/editar/{id}")
    public String mostrarEditar(@PathVariable Long id, Model model) {
        model.addAttribute("categoria", categoriaService.findById(id).orElseThrow());
        return "admin/categorias-form-admin";
    }

    @PostMapping({"/crear", "/editar/{id}"})
    public String guardar(@ModelAttribute Categoria categoria) {
        categoriaService.save(categoria);
        return "redirect:/admin/categorias";
    }

    @GetMapping("/borrar/{id}")
    public String confirmarBorrado(@PathVariable Long id, Model model) {
        categoriaService.findById(id).ifPresent(c -> model.addAttribute("categoria", c));
        return "admin/categorias-borrar-confirmar";
    }

    @PostMapping("/borrar/{id}")
    public String eliminar(@PathVariable Long id, Model model) {
        try {
            categoriaService.deleteById(id);
            return "redirect:/admin/categorias";
        } catch (Exception e) {
            model.addAttribute("error", "No se puede eliminar la categoría: existen productos vinculados a ella.");
            categoriaService.findById(id).ifPresent(c -> model.addAttribute("categoria", c));
            return "admin/categorias-borrar-confirmar";
        }
    }
}