package es.iesclaradelrey.da2d1a.common.repositories;

import es.iesclaradelrey.da2d1a.common.entities.Producto;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findByCategorias_Id(Long categoriaId, Sort sort);
}
