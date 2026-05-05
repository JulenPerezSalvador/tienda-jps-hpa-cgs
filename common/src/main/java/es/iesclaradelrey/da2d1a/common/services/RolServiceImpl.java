package es.iesclaradelrey.da2d1a.common.services;

import es.iesclaradelrey.da2d1a.common.entities.Rol;
import es.iesclaradelrey.da2d1a.common.repositories.IRolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RolServiceImpl implements IRolService {

    @Autowired
    private IRolRepository repository;

    @Override
    public Optional<Rol> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Rol save(Rol rol) {
        return repository.save(rol);
    }
}
