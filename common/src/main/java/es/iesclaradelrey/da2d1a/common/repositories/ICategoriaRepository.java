package es.iesclaradelrey.da2d1a.common.repositories;

import es.iesclaradelrey.da2d1a.common.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoriaRepository extends JpaRepository<Categoria, Long> {
}
