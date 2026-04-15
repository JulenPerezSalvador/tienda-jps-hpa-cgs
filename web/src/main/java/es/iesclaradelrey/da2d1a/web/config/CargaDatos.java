package es.iesclaradelrey.da2d1a.web.config;

import es.iesclaradelrey.da2d1a.common.entities.Categoria;
import es.iesclaradelrey.da2d1a.common.services.ICategoriaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
//Le dice lee esta clase que tengo cosas importantes para ti
public class CargaDatos implements CommandLineRunner {

    private final ICategoriaService categoriaService;

    public CargaDatos(ICategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @Override
    public void run(String... args) {
        categoriaService.save(new Categoria(1L, "Camisetas", "Camisetas de algodón orgánico", "ImagenNoDisponible.png"));
        categoriaService.save(new Categoria(2L, "Pantalones", "Vaqueros y pantalones chinos", null));
        categoriaService.save(new Categoria(3L, "Abrigos", "Chaquetas para el invierno", null));
        categoriaService.save(new Categoria(4L, "Accesorios", "Cinturones y bufandas", null));
        categoriaService.save(new Categoria(5L, "Calzado", "Zapatillas", null));
        categoriaService.save(new Categoria(6L, "Gorras", "Gorras", null));
    }
}