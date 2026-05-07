package es.iesclaradelrey.da2d1a.api.mappers;

import es.iesclaradelrey.da2d1a.api.dto.CategoriaDTO;
import es.iesclaradelrey.da2d1a.common.entities.Categoria;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {
    CategoriaDTO toDto(Categoria categoria);
    List<CategoriaDTO> toDtoList(List<Categoria> categorias);
}
