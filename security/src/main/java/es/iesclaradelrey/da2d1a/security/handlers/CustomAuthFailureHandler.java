package es.iesclaradelrey.da2d1a.security.handlers;

import es.iesclaradelrey.da2d1a.common.entities.EventoSeguridad.TipoEvento;
import es.iesclaradelrey.da2d1a.common.services.IEventoSeguridadService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private IEventoSeguridadService eventoService;

    public CustomAuthFailureHandler() {
        super("/login?error");
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception)
            throws IOException, ServletException {

        String username = request.getParameter("username");
        if (username == null || username.isBlank()) {
            username = "desconocido";
        }
        eventoService.registrarEvento(username, TipoEvento.LOGIN_ERROR);
        super.onAuthenticationFailure(request, response, exception);
    }
}
