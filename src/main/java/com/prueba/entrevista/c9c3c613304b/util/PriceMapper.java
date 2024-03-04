package com.prueba.entrevista.c9c3c613304b.util;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.prueba.entrevista.c9c3c613304b.dtos.PriceDTO;
import com.prueba.entrevista.c9c3c613304b.entities.PriceEntity;

@Mapper(componentModel = "spring")
public abstract class PriceMapper {
    
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "brand.id", source = "brandID")
    @Mapping(target = "product.id", source = "productID")
    public abstract void updatePriceFromDTO(PriceDTO dto, @MappingTarget PriceEntity entity);
}
