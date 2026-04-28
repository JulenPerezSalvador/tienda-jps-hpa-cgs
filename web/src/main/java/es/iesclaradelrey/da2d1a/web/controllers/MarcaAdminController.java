package es.iesclaradelrey.da2d1a.web.controllers;

import es.iesclaradelrey.da2d1a.common.entities.Marca;
import es.iesclaradelrey.da2d1a.common.services.IMarcaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/marcas")
public class MarcaAdminController {

    private final IMarcaService marcaService;

    public MarcaAdminController(IMarcaService marcaService) {
        this.marcaService = marcaService;
    }

    @GetMapping("")
    public String listado(Model model) {
        model.addAttribute("marcas", marcaService.findAll());
        return "admin/marcas-listado-admin";
    }

    @GetMapping("/crear")
    public String mostrarCrear(Model model) {
        model.addAttribute("marca", new Marca());
        return "admin/marcas-form-admin";
    }

    @GetMapping("/editar/{id}")
    public String mostrarEditar(@PathVariable Long id, Model model) {
        model.addAttribute("marca", marcaService.findById(id).orElseThrow());
        return "admin/marcas-form-admin";
    }

    @PostMapping({"/crear", "/editar/{id}"})
    public String guardar(@ModelAttribute Marca marca) {
        marcaService.save(marca);
        return "redirect:/admin/marcas";
    }

    @GetMapping("/borrar/{id}")
    public String confirmarBorrado(@PathVariable Long id, Model model) {
        marcaService.findById(id).ifPresent(m -> model.addAttribute("marca", m));
        return "admin/marcas-borrar-confirmar";
    }

    @PostMapping("/borrar/{id}")
    public String eliminar(@PathVariable Long id, Model model) {
        try {
            marcaService.deleteById(id);
            return "redirect:/admin/marcas";
        } catch (Exception e) {
            model.addAttribute("error", "No se puede eliminar: Hay productos asociados a esta marca.");
            marcaService.findById(id).ifPresent(m -> model.addAttribute("marca", m));
            return "admin/marcas-borrar-confirmar";
        }
    }
}