package es.iesclaradelrey.da2d1a.common.services;

import es.iesclaradelrey.da2d1a.common.entities.Categoria;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface ICategoriaService {
    List<Categoria> findAll();
    List<Categoria> findAll(Sort sort);
    Optional<Categoria> findById(Long id);
    Categoria save(Categoria categoria);
    void deleteById(Long id);
}
