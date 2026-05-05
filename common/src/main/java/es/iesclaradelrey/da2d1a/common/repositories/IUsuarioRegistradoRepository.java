package es.iesclaradelrey.da2d1a.common.repositories;

import es.iesclaradelrey.da2d1a.common.entities.UsuarioRegistrado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUsuarioRegistradoRepository extends JpaRepository<UsuarioRegistrado, Long> {

    Optional<UsuarioRegistrado> findByEmail(String email);

    boolean existsByEmail(String email);
}
