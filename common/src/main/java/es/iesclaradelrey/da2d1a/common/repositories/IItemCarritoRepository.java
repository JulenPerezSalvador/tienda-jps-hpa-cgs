package es.iesclaradelrey.da2d1a.common.repositories;

import es.iesclaradelrey.da2d1a.common.entities.ItemCarrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IItemCarritoRepository extends JpaRepository<ItemCarrito, Long> {

    List<ItemCarrito> findByUsuarioId(Long usuarioId);

    Optional<ItemCarrito> findByUsuarioIdAndProductoId(Long usuarioId, Long productoId);

    void deleteByUsuarioIdAndProductoId(Long usuarioId, Long productoId);

    void deleteByUsuarioId(Long usuarioId);


    @Query("SELECT COUNT(i) FROM ItemCarrito i WHERE i.usuario.id = :usuarioId")
    Long countProductosByUsuarioId(@Param("usuarioId") Long usuarioId);

    @Query("SELECT COALESCE(SUM(i.unidades), 0) FROM ItemCarrito i WHERE i.usuario.id = :usuarioId")
    Long sumUnidadesByUsuarioId(@Param("usuarioId") Long usuarioId);

    @Query("SELECT COALESCE(SUM(i.unidades * i.producto.precio * (1 - i.producto.descuento / 100.0)), 0.0) " +
           "FROM ItemCarrito i WHERE i.usuario.id = :usuarioId")
    Double calcularImporteTotalByUsuarioId(@Param("usuarioId") Long usuarioId);
}
