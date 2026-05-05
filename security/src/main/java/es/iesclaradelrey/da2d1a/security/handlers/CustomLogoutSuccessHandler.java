package es.iesclaradelrey.da2d1a.security.handlers;

import es.iesclaradelrey.da2d1a.common.entities.EventoSeguridad.TipoEvento;
import es.iesclaradelrey.da2d1a.common.services.IEventoSeguridadService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

    @Autowired
    private IEventoSeguridadService eventoService;

    public CustomLogoutSuccessHandler() {
        setDefaultTargetUrl("/");
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication)
            throws IOException, ServletException {

        if (authentication != null) {
            eventoService.registrarEvento(authentication.getName(), TipoEvento.LOGOUT);
        }
        super.onLogoutSuccess(request, response, authentication);
    }
}
