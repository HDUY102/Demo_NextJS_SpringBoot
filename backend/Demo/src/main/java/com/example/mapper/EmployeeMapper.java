package com.example.mapper;

import org.mapstruct.*;import com.example.demo.entity.*;
import com.example.demo.generics.BaseMapper;
import com.example.dtos.EmployeeDTO;

@Mapper(componentModel = "spring",
		nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EmployeeMapper extends BaseMapper<Employees, EmployeeDTO, EmployeeDTO>{
	@Override
	@Mapping(target ="name", source = "nameEmployee")
	EmployeeDTO toDTO(Employees employee);
	
	@Override
	@Mapping(target = "nameEmployee", source = "name")
	@Mapping(target = "id", ignore = true)
	Employees toEntity(EmployeeDTO employeeDTO);
	
	@Override
	@Mapping(target = "nameEmployee", source = "name")
    @Mapping(target = "addressEmployee", source = "addressEmployee")
    @Mapping(target = "phoneEmployee", source = "phoneEmployee")
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "role", ignore = true)
	void updateEntityFromDto(EmployeeDTO employeeDTO,@MappingTarget Employees employee);	
}