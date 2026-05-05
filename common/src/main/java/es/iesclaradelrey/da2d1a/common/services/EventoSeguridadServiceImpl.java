package es.iesclaradelrey.da2d1a.common.services;

import es.iesclaradelrey.da2d1a.common.entities.EventoSeguridad;
import es.iesclaradelrey.da2d1a.common.entities.EventoSeguridad.TipoEvento;
import es.iesclaradelrey.da2d1a.common.repositories.IEventoSeguridadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EventoSeguridadServiceImpl implements IEventoSeguridadService {

    @Autowired
    private IEventoSeguridadRepository repository;

    @Override
    public void registrarEvento(String nombreUsuario, TipoEvento tipo) {
        EventoSeguridad evento = new EventoSeguridad();
        evento.setFechaHora(LocalDateTime.now());
        evento.setNombreUsuario(nombreUsuario);
        evento.setTipo(tipo);
        repository.save(evento);
    }

    @Override
    public EventoSeguridad save(EventoSeguridad evento) {
        return repository.save(evento);
    }
}
