package es.iesclaradelrey.da2d1a.common.services;

import es.iesclaradelrey.da2d1a.common.entities.ItemCarrito;

import java.util.List;
import java.util.Optional;

public interface IItemCarritoService {
    List<ItemCarrito> findByUsuarioId(Long usuarioId);
    Optional<ItemCarrito> findByUsuarioIdAndProductoId(Long usuarioId, Long productoId);
    ItemCarrito save(ItemCarrito item);
    void deleteByUsuarioIdAndProductoId(Long usuarioId, Long productoId);
    void deleteByUsuarioId(Long usuarioId);
    Long countProductosByUsuarioId(Long usuarioId);
    Long sumUnidadesByUsuarioId(Long usuarioId);
    Double calcularImporteTotalByUsuarioId(Long usuarioId);
}
