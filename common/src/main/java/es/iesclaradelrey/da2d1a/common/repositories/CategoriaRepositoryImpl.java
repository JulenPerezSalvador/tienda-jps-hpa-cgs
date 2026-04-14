package es.iesclaradelrey.da2d1a.common.repositories;

import es.iesclaradelrey.da2d1a.common.entities.Categoria;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

//Parece igual que service pero no es lo mismoes la parte que guarda o saca la imformacion
//Es la parte de base de datos
@Repository
public class CategoriaRepositoryImpl implements ICategoriaRepository {

    private final Map<Long, Categoria> categorias = new HashMap<>();

    @Override
    public Collection<Categoria> findAll() {
        return categorias.values();
    }

    @Override
    public Optional<Categoria> findById(Long id) {
        return Optional.ofNullable(categorias.get(id));
    }

    @Override
    public void save(Categoria categoria) {
        categorias.put(categoria.getId(), categoria);
    }

    @Override
    public void deleteById(Long id) {
        categorias.remove(id);
    }
}