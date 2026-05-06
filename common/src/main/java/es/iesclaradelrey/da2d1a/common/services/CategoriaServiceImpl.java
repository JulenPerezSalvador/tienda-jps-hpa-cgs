package es.iesclaradelrey.da2d1a.common.services;

import es.iesclaradelrey.da2d1a.common.entities.Categoria;
import es.iesclaradelrey.da2d1a.common.repositories.ICategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements ICategoriaService {

    @Autowired private ICategoriaRepository repository;

    @Override public List<Categoria> findAll() { return repository.findAll(); }
    @Override public List<Categoria> findAll(Sort sort) { return repository.findAll(sort); }
    @Override public Optional<Categoria> findById(Long id) { return repository.findById(id); }
    @Override public Categoria save(Categoria c) { return repository.save(c); }
    @Override public void deleteById(Long id) { repository.deleteById(id); }
}
