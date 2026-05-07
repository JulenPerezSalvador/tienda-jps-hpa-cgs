package es.iesclaradelrey.da2d1a.api.dto;

import lombok.Data;

@Data
public class CartItemDTO {
    private Long productoId;
    private String nombreProducto;
    private Double precioUnitario;
    private Integer descuento;
    private Double precioConDescuento;
    private Integer unidades;
    private Double precioTotal;
}
