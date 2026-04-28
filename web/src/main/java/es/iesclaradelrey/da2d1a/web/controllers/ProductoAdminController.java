package es.iesclaradelrey.da2d1a.web.controllers;

import es.iesclaradelrey.da2d1a.common.entities.Producto;
import es.iesclaradelrey.da2d1a.common.services.IProductoService;
import es.iesclaradelrey.da2d1a.common.services.IMarcaService;
import es.iesclaradelrey.da2d1a.common.services.ICategoriaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/productos")
public class ProductoAdminController {

    private final IProductoService productoService;
    private final IMarcaService marcaService;
    private final ICategoriaService categoriaService;

    public ProductoAdminController(IProductoService productoService, IMarcaService marcaService, ICategoriaService categoriaService) {
        this.productoService = productoService;
        this.marcaService = marcaService;
        this.categoriaService = categoriaService;
    }

    @GetMapping("")
    public String listado(Model model) {
        model.addAttribute("productos", productoService.findAll());
        return "admin/productos-listado-admin";
    }

    @GetMapping("/crear")
    public String mostrarCrear(Model model) {
        model.addAttribute("producto", new Producto());
        cargarAuxiliares(model);
        return "admin/productos-form-admin";
    }

    @GetMapping("/editar/{id}")
    public String mostrarEditar(@PathVariable Long id, Model model) {
        Producto producto = productoService.findById(id).orElseThrow();
        model.addAttribute("producto", producto);
        cargarAuxiliares(model);
        return "admin/productos-form-admin";
    }

    @PostMapping({"/crear", "/editar/{id}"})
    public String guardar(@ModelAttribute("producto") Producto producto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            System.out.println("ERRORES EN FORMULARIO: " + result.getAllErrors());
            cargarAuxiliares(model);
            return "admin/productos-form-admin";
        }
        productoService.save(producto);
        return "redirect:/admin/productos";
    }

    @GetMapping("/borrar/{id}")
    public String confirmarBorrado(@PathVariable Long id, Model model) {
        productoService.findById(id).ifPresent(p -> model.addAttribute("producto", p));
        return "admin/productos-borrar-confirmar";
    }

    @PostMapping("/borrar/{id}")
    public String eliminar(@PathVariable Long id) {
        productoService.deleteById(id);
        return "redirect:/admin/productos";
    }

    private void cargarAuxiliares(Model model) {
        model.addAttribute("marcas", marcaService.findAll());
        model.addAttribute("categorias", categoriaService.findAll());
    }
}