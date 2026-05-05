package es.iesclaradelrey.da2d1a.security.handlers;

import es.iesclaradelrey.da2d1a.common.entities.EventoSeguridad.TipoEvento;
import es.iesclaradelrey.da2d1a.common.services.IEventoSeguridadService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private IEventoSeguridadService eventoService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        eventoService.registrarEvento(authentication.getName(), TipoEvento.LOGIN);

        boolean esAdmin = authentication.getAuthorities()
                .contains(new SimpleGrantedAuthority("ROLE_ADMIN"));

        String destino = esAdmin ? "/admin" : "/";
        getRedirectStrategy().sendRedirect(request, response, destino);
    }
}