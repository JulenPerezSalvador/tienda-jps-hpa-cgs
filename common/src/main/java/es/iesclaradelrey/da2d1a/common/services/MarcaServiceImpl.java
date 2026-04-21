package es.iesclaradelrey.da2d1a.common.services;

import es.iesclaradelrey.da2d1a.common.entities.Marca;
import es.iesclaradelrey.da2d1a.common.repositories.IMarcaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarcaServiceImpl implements IMarcaService {

    private final IMarcaRepository repository;

    public MarcaServiceImpl(IMarcaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Marca> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Marca> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void save(Marca marca) {
        repository.save(marca);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}