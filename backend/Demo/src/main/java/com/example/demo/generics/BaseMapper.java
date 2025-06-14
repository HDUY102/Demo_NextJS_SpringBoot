package com.example.demo.generics;

import java.util.List;
import org.mapstruct.MappingTarget;

public interface BaseMapper<E, ReqDTO, ResDTO> {
	E toEntity(ReqDTO genericDTO);

    ResDTO toDTO(E entity);

    default List<ResDTO> toDtoList(List<E> entityList) {
        if (entityList == null) {
            return null;
        }
        return entityList.stream()
                         .map(this::toDTO)
                         .collect(java.util.stream.Collectors.toList());
    }

    public abstract void updateEntityFromDto(ReqDTO dto, @MappingTarget E entity);
}