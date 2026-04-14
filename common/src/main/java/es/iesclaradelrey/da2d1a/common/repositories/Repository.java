package es.iesclaradelrey.da2d1a.common.repositories;

import java.util.Collection;
import java.util.Optional;

//Este es el patron de repositorio generico y los metodos son CRUD
public interface Repository<T, K> {
    Collection<T> findAll();
    Optional<T> findById(K id);
    void save(T entity);
    void deleteById(K id);
}