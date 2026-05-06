package es.iesclaradelrey.da2d1a.common.services;

import es.iesclaradelrey.da2d1a.common.entities.ItemCarrito;
import es.iesclaradelrey.da2d1a.common.repositories.IItemCarritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ItemCarritoServiceImpl implements IItemCarritoService {

    @Autowired
    private IItemCarritoRepository repository;

    @Override public List<ItemCarrito> findByUsuarioId(Long usuarioId) {
        return repository.findByUsuarioId(usuarioId);
    }

    @Override public Optional<ItemCarrito> findByUsuarioIdAndProductoId(Long usuarioId, Long productoId) {
        return repository.findByUsuarioIdAndProductoId(usuarioId, productoId);
    }

    @Override public ItemCarrito save(ItemCarrito item) {
        return repository.save(item);
    }

    @Override @Transactional
    public void deleteByUsuarioIdAndProductoId(Long usuarioId, Long productoId) {
        repository.deleteByUsuarioIdAndProductoId(usuarioId, productoId);
    }

    @Override @Transactional
    public void deleteByUsuarioId(Long usuarioId) {
        repository.deleteByUsuarioId(usuarioId);
    }

    @Override public Long countProductosByUsuarioId(Long usuarioId) {
        return repository.countProductosByUsuarioId(usuarioId);
    }

    @Override public Long sumUnidadesByUsuarioId(Long usuarioId) {
        return repository.sumUnidadesByUsuarioId(usuarioId);
    }

    @Override public Double calcularImporteTotalByUsuarioId(Long usuarioId) {
        return repository.calcularImporteTotalByUsuarioId(usuarioId);
    }
}
