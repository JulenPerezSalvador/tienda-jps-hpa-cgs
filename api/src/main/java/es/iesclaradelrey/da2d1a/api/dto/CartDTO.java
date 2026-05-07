package es.iesclaradelrey.da2d1a.api.dto;

import lombok.Data;
import java.util.List;

@Data
public class CartDTO {
    private List<CartItemDTO> items;
    private Long numProductosDistintos;
    private Long numUnidadesTotales;
    private Double importeTotal;
}
