package com.example.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import com.example.demo.entity.FlowerPrice;
import com.example.dtos.FlowerPriceDTO;

@Mapper(componentModel = "spring",
nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface FlowerPriceMapper{
	@Mapping(target="id.flowerTypeId", source="flowerTypeId")
	@Mapping(target="id.saleUnitId",source="saleUnitId")
	@Mapping(target="flowerType", ignore=true)
	@Mapping(target="saleUnit", ignore=true)
	@Mapping(target="priceOfUnit", source="priceOfUnit")
	@Mapping(target="updatedAt", source="updatedAt")
	FlowerPrice toEntity(FlowerPriceDTO flowerPriceDTO);
	
	@Mapping(target="flowerTypeId", source="id.flowerTypeId")
	@Mapping(target="saleUnitId",source="id.saleUnitId")
	@Mapping(target="priceOfUnit", source="priceOfUnit")
	@Mapping(target="updatedAt", source="updatedAt")
	@Mapping(target="saleUnitName", source="saleUnit.nameSaleUnit")
	@Mapping(target="flowerTypeName", source="flowerType.nameFlowerType")
	FlowerPriceDTO toDTO(FlowerPrice flowerPrice);
	
	List<FlowerPriceDTO> toDTOList(List<FlowerPrice> detailOrderList);
	
	@Mapping(target="id", ignore=true)
    @Mapping(target="flowerType", ignore=true)
    @Mapping(target="saleUnit", ignore=true)
    @Mapping(target="priceOfUnit", source="priceOfUnit")
    @Mapping(target="updatedAt", source="updatedAt")
    void updateEntityFromDto(FlowerPriceDTO dto, @MappingTarget FlowerPrice entity);
}