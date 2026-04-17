package es.iesclaradelrey.da2d1a.common.services;

import es.iesclaradelrey.da2d1a.common.entities.Categoria;
import es.iesclaradelrey.da2d1a.common.repositories.ICategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements ICategoriaService {

    private final ICategoriaRepository repository;

    // Inyección por constructor
    public CategoriaServiceImpl(ICategoriaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Categoria> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Categoria> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void save(Categoria categoria) {
        repository.save(categoria);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}