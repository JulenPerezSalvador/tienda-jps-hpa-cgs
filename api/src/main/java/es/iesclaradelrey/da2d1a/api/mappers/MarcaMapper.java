package es.iesclaradelrey.da2d1a.api.mappers;

import es.iesclaradelrey.da2d1a.api.dto.MarcaDTO;
import es.iesclaradelrey.da2d1a.common.entities.Marca;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MarcaMapper {
    MarcaDTO toDto(Marca marca);
}
