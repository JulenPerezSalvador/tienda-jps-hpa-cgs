package es.iesclaradelrey.da2d1a.common.services;

import es.iesclaradelrey.da2d1a.common.entities.Rol;
import java.util.Optional;

public interface IRolService {
    Optional<Rol> findById(String id);
    Rol save(Rol rol);
}
