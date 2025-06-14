package com.example.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import com.example.demo.entity.Orders;
import com.example.demo.generics.BaseMapper;
import com.example.dtos.OrderDTO;

@Mapper(componentModel = "spring",
nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderMapper extends BaseMapper<Orders, OrderDTO, OrderDTO>{
	@Override
	@Mapping(target = "customer", ignore = true) // Quan trọng cho toEntity/update
    @Mapping(target = "currentStatus", ignore = true) // Quan trọng cho toEntity/update
	@Mapping(target = "dateOrder", source = "dateOrder")
    @Mapping(target = "isPaid", source = "isPaid")
    @Mapping(target = "totalAmount", source = "totalAmount")
	Orders toEntity(OrderDTO orderDTO);
	
	@Override
	@Mapping(target = "name", source = "customer.nameCustomer") // Ánh xạ trường name chung
	@Mapping(target = "dateOrder", source = "dateOrder")
    @Mapping(target = "isPaid", source = "isPaid")
    @Mapping(target = "totalAmount", source = "totalAmount")
	@Mapping(target = "customerId", source = "customer.id") // Ánh xạ ID của mối quan hệ
    @Mapping(target = "currentStatusId", source = "currentStatus.id") // Ánh xạ ID của mối quan hệ
	OrderDTO toDTO(Orders ordersEntity);
	
	@Override
	@Mapping(target = "id", ignore = true) // Quan trọng cho updateEntityFromDto
	@Mapping(target = "customer", ignore = true) // Quan trọng cho updateEntityFromDto
    @Mapping(target = "currentStatus", ignore = true) // Quan trọng cho updateEntityFromDto
	@Mapping(target = "dateOrder", source = "dateOrder")
    @Mapping(target = "isPaid", source = "isPaid")
    @Mapping(target = "totalAmount", source = "totalAmount")
    void updateEntityFromDto(OrderDTO dto, @MappingTarget Orders entity);
}