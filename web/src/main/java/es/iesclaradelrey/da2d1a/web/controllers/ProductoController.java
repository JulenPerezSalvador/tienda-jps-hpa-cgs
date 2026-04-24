package es.iesclaradelrey.da2d1a.web.controllers;

import es.iesclaradelrey.da2d1a.common.services.IProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    private final IProductoService productoService;

    public ProductoController(IProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping({"", "/"})
    public String listar(Model model) {
        model.addAttribute("productos", productoService.findAll());
        return "productos-listado";
    }

    @GetMapping("/{id}/{nombreEscapado}")
    public String detalle(@PathVariable("id") Long id, @PathVariable("nombreEscapado") String nombreEscapado, Model model) {
        productoService.findById(id).ifPresentOrElse(
                p -> model.addAttribute("producto", p),
                () -> { throw new RuntimeException("Producto no encontrado"); }
        );
        return "producto-detalle";
    }
}