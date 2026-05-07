package es.iesclaradelrey.da2d1a.api.dto;

import lombok.Data;
import java.util.List;

@Data
public class ProductoDTO {
    private Long id;
    private String codigoEan;
    private String nombre;
    private String descripcion;
    private String imagen;
    private Double precio;
    private Integer descuento;
    private Integer stock;
    private MarcaDTO marca;
    private List<CategoriaDTO> categorias;
}
