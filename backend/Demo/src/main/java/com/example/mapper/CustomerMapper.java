package com.example.mapper;

import org.mapstruct.*;
import com.example.demo.entity.Customers;
import com.example.demo.generics.BaseMapper;
import com.example.dtos.CustomerDTO;

@Mapper(componentModel = "spring",
	nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CustomerMapper extends BaseMapper<Customers, CustomerDTO, CustomerDTO> {
	@Override
    @Mapping(target = "name", source = "nameCustomer") // ánh xạ nameCustomer -> name trong BaseDTO
	CustomerDTO toDTO(Customers customerEntity);

	@Override
    @Mapping(source = "name", target = "nameCustomer") // Ánh xạ name của GenericDTO sang nameCustomer của Entity
    @Mapping(target = "id", ignore = true)
	Customers toEntity(CustomerDTO customerDTO);

    @Override
    @Mapping(source = "name", target = "nameCustomer")
    @Mapping(target = "address", source = "address")
    @Mapping(target = "phoneNumber", source = "phoneNumber")
    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(CustomerDTO dto, @MappingTarget Customers entity);
}