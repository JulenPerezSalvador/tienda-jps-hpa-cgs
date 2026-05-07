package es.iesclaradelrey.da2d1a.api.dto;

import lombok.Data;

@Data
public class AddToCartDTO {
    private Long productoId;
    private Integer unidades;
}
