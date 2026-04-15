package es.iesclaradelrey.da2d1a.common.services;

import es.iesclaradelrey.da2d1a.common.entities.Categoria;

import java.util.Collection;
import java.util.Optional;

public interface ICategoriaService {
    Collection<Categoria> findAll();
    Optional<Categoria> findById(Long id);
    void save(Categoria categoria);
    void deleteById(Long id);
}