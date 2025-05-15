package com.example.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.example.dtos.CustomerDTO;
import com.example.entity.Customers;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
	CustomerDTO toDTO(Customers customer);
	Customers toEntity(CustomerDTO customerDTO);
	List<CustomerDTO> toDTOs(List<Customers> customers);
}
