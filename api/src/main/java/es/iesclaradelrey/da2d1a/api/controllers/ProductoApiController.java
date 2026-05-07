package es.iesclaradelrey.da2d1a.api.controllers;

import es.iesclaradelrey.da2d1a.api.dto.ProductoDTO;
import es.iesclaradelrey.da2d1a.api.mappers.ProductoMapper;
import es.iesclaradelrey.da2d1a.common.services.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProductoApiController {

    @Autowired private IProductoService productoService;
    @Autowired private ProductoMapper productoMapper;

    @GetMapping("/products")
    public List<ProductoDTO> listarProductos() {
        Sort sort = Sort.by(Sort.Direction.ASC, "nombre");
        return productoMapper.toDtoList(productoService.findAll(sort));
    }

    @GetMapping("/categories/{categoryId}/products")
    public List<ProductoDTO> productosPorCategoria(@PathVariable Long categoryId) {
        Sort sort = Sort.by(Sort.Direction.ASC, "nombre");
        return productoMapper.toDtoList(productoService.findByCategoriaId(categoryId, sort));
    }
}
