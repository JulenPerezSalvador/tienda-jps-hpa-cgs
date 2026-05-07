package es.iesclaradelrey.da2d1a.api.controllers;

import es.iesclaradelrey.da2d1a.api.dto.CategoriaDTO;
import es.iesclaradelrey.da2d1a.api.mappers.CategoriaMapper;
import es.iesclaradelrey.da2d1a.common.services.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoriaApiController {

    @Autowired private ICategoriaService categoriaService;
    @Autowired private CategoriaMapper categoriaMapper;

    @GetMapping
    public List<CategoriaDTO> listarCategorias() {
        Sort sort = Sort.by(Sort.Direction.ASC, "nombre");
        return categoriaMapper.toDtoList(categoriaService.findAll(sort));
    }
}
