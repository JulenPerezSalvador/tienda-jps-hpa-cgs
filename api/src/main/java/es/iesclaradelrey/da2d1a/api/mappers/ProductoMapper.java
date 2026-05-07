package es.iesclaradelrey.da2d1a.api.mappers;

import es.iesclaradelrey.da2d1a.api.dto.ProductoDTO;
import es.iesclaradelrey.da2d1a.common.entities.Producto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {MarcaMapper.class, CategoriaMapper.class})
public interface ProductoMapper {
    ProductoDTO toDto(Producto producto);
    List<ProductoDTO> toDtoList(List<Producto> productos);
}
