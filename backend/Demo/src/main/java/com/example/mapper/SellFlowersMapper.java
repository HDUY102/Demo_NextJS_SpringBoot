package com.example.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import com.example.demo.entity.SellFlowers;
import com.example.dtos.SellFlowersDTO;

@Mapper(componentModel = "spring")
public interface SellFlowersMapper {
	SellFlowers toEntitySellFlowers(SellFlowersDTO sellFlowersDTO);
	
	SellFlowersDTO toDTOSellFlowers(SellFlowers sellFlowersEntity);
	
	List<SellFlowersDTO> toDTOList(List<SellFlowers> sellFlowersEntityList);
}
