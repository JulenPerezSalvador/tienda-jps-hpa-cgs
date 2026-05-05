package es.iesclaradelrey.da2d1a.common.services;

import es.iesclaradelrey.da2d1a.common.entities.UsuarioRegistrado;
import es.iesclaradelrey.da2d1a.common.repositories.IUsuarioRegistradoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioRegistradoServiceImpl implements IUsuarioRegistradoService {

    @Autowired
    private IUsuarioRegistradoRepository repository;

    @Override
    public UsuarioRegistrado save(UsuarioRegistrado usuario) {
        return repository.save(usuario);
    }

    @Override
    public Optional<UsuarioRegistrado> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public Optional<UsuarioRegistrado> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<UsuarioRegistrado> findAll() {
        return repository.findAll();
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }
}
