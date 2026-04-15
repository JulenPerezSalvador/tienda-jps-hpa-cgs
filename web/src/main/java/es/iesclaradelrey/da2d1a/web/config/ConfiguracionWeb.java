package es.iesclaradelrey.da2d1a.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//Es un atajo ya que no tendria mucho sentido crear un controller
@Configuration
public class ConfiguracionWeb implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/condiciones").setViewName("condiciones");
    }
}