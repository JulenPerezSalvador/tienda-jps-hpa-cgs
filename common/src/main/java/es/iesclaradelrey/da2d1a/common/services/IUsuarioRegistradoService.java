package es.iesclaradelrey.da2d1a.common.services;

import es.iesclaradelrey.da2d1a.common.entities.UsuarioRegistrado;

import java.util.List;
import java.util.Optional;

public interface IUsuarioRegistradoService {
    UsuarioRegistrado save(UsuarioRegistrado usuario);
    Optional<UsuarioRegistrado> findByEmail(String email);
    Optional<UsuarioRegistrado> findById(Long id);
    List<UsuarioRegistrado> findAll();
    boolean existsByEmail(String email);
}
