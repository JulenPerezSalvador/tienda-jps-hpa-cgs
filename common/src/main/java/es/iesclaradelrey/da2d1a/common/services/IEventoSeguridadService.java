package es.iesclaradelrey.da2d1a.common.services;

import es.iesclaradelrey.da2d1a.common.entities.EventoSeguridad;
import es.iesclaradelrey.da2d1a.common.entities.EventoSeguridad.TipoEvento;

public interface IEventoSeguridadService {
    void registrarEvento(String nombreUsuario, TipoEvento tipo);
    EventoSeguridad save(EventoSeguridad evento);
}
