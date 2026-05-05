package es.iesclaradelrey.da2d1a.common.repositories;

import es.iesclaradelrey.da2d1a.common.entities.EventoSeguridad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEventoSeguridadRepository extends JpaRepository<EventoSeguridad, Long> {
}
