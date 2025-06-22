package com.example.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import com.example.demo.entity.Orders;
import com.example.demo.generics.BaseMapper;
import com.example.dtos.OrderDTO;

@Mapper(componentModel = "spring",uses = {DetailOrderMapper.class},
nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderMapper extends BaseMapper<Orders, OrderDTO, OrderDTO>{
	@Override
	@Mapping(target = "customer", ignore = true)
    @Mapping(target = "currentStatus", ignore = true)
	@Mapping(target = "orderHistoryJson", ignore = true)
	@Mapping(target = "details", ignore = true)
    @Mapping(target = "totalAmount", ignore = true)
	@Mapping(target = "dateOrder", source = "dateOrder")
    @Mapping(target = "isPaid", source = "isPaid")
	Orders toEntity(OrderDTO orderDTO);
	
	@Override
	@Mapping(target = "name", source = "customer.nameCustomer") // Ánh xạ trường name chung
	@Mapping(target = "dateOrder", source = "dateOrder")
    @Mapping(target = "isPaid", source = "isPaid")
    @Mapping(target = "totalAmount", source = "totalAmount")
	@Mapping(target = "customerId", source = "customer.id") // Ánh xạ ID của mối quan hệ
    @Mapping(target = "currentStatusId", source = "currentStatus.id")
	@Mapping(target = "orderHistoryJson", source = "orderHistoryJson")
	@Mapping(target = "details", source = "details")
	@Mapping(source = "customer.nameCustomer", target = "customerName") 
	OrderDTO toDTO(Orders ordersEntity);
	
	@Override
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "customer", ignore = true)
    @Mapping(target = "currentStatus", ignore = true)
	@Mapping(target = "orderHistoryJson", ignore = true)
	@Mapping(target = "totalAmount", ignore = true)
	@Mapping(target = "details", ignore = true)
	@Mapping(target = "dateOrder", ignore = true)
    @Mapping(target = "isPaid", source = "isPaid")
    void updateEntityFromDto(OrderDTO dto, @MappingTarget Orders entity);
}