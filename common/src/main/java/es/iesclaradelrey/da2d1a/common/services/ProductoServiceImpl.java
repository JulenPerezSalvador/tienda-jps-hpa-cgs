package es.iesclaradelrey.da2d1a.common.services;

import es.iesclaradelrey.da2d1a.common.entities.Producto;
import es.iesclaradelrey.da2d1a.common.repositories.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements IProductoService {

    @Autowired private IProductoRepository repository;

    @Override public List<Producto> findAll() { return repository.findAll(); }
    @Override public List<Producto> findAll(Sort sort) { return repository.findAll(sort); }
    @Override public List<Producto> findByCategoriaId(Long categoriaId, Sort sort) {
        return repository.findByCategorias_Id(categoriaId, sort);
    }
    @Override public Optional<Producto> findById(Long id) { return repository.findById(id); }
    @Override public Producto save(Producto p) { return repository.save(p); }
    @Override public void deleteById(Long id) { repository.deleteById(id); }
}
