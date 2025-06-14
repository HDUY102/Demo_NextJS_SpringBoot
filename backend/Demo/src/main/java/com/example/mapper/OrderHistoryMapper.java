package com.example.mapper;

import org.mapstruct.*;

import com.example.demo.entity.OrderHistory;
import com.example.demo.generics.BaseMapper;
import com.example.dtos.OrderHistoryDTO;

@Mapper(componentModel = "spring",
nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderHistoryMapper extends BaseMapper<OrderHistory, OrderHistoryDTO, OrderHistoryDTO>{
	@Override
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "idOrder_History.customer.nameCustomer")
	@Mapping(target = "changedAt", source = "changedAt", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "changedBy", source = "changedBy")
    @Mapping(target = "note", source = "note")
    @Mapping(target = "orderId", source = "idOrder_History.id")
    @Mapping(target = "statusId", source = "idStatus_History.id")
    @Mapping(target = "customerName", source = "idOrder_History.customer.nameCustomer")
    @Mapping(target = "orderDate", source = "idOrder_History.dateOrder", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "orderStatusCode", source = "idStatus_History.statusCode")
    @Mapping(target = "orderStatusName", source = "idStatus_History.statusName")
    OrderHistoryDTO toDTO(OrderHistory orderHistoryEntity);

    // --- ------------------ ---
    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "idOrder_History", ignore = true)
    @Mapping(target = "idStatus_History", ignore = true)
    @Mapping(target = "changedAt", source = "changedAt")
    @Mapping(target = "changedBy", source = "changedBy")
    @Mapping(target = "note", source = "note")
    @Mapping(target = "idOrder_History.customer.nameCustomer", source = "name")
    OrderHistory toEntity(OrderHistoryDTO orderHistoryDTO);

    // --- Phương thức cập nhật Entity từ DTO (OrderHistoryDTO -> OrderHistory) ---
    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "idOrder_History", ignore = true)
    @Mapping(target = "idStatus_History", ignore = true)
    @Mapping(target = "changedAt", source = "changedAt")
    @Mapping(target = "changedBy", source = "changedBy")
    @Mapping(target = "note", source = "note")
    void updateEntityFromDto(OrderHistoryDTO dto, @MappingTarget OrderHistory entity);
}