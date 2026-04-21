package es.iesclaradelrey.da2d1a.common.services;

import es.iesclaradelrey.da2d1a.common.entities.Marca;
import java.util.List;
import java.util.Optional;

public interface IMarcaService {
    List<Marca> findAll();
    Optional<Marca> findById(Long id);
    void save(Marca marca);
    void deleteById(Long id);
}