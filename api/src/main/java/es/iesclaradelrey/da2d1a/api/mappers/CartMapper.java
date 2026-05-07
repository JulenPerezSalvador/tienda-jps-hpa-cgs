package es.iesclaradelrey.da2d1a.api.mappers;

import es.iesclaradelrey.da2d1a.api.dto.CartItemDTO;
import es.iesclaradelrey.da2d1a.common.entities.ItemCarrito;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {

    @Mapping(target = "productoId",       source = "producto.id")
    @Mapping(target = "nombreProducto",   source = "producto.nombre")
    @Mapping(target = "precioUnitario",   source = "producto.precio")
    @Mapping(target = "descuento",        source = "producto.descuento")
    @Mapping(target = "precioConDescuento", expression =
        "java(item.getProducto().getPrecio() * (1 - item.getProducto().getDescuento() / 100.0))")
    @Mapping(target = "precioTotal", expression =
        "java(item.getUnidades() * item.getProducto().getPrecio() * (1 - item.getProducto().getDescuento() / 100.0))")
    CartItemDTO toDto(ItemCarrito item);
}
