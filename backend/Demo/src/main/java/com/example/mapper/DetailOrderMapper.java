package com.example.mapper;

import java.util.List;
import org.mapstruct.*;
import com.example.demo.entity.DetailOrder;
import com.example.dtos.DetailOrderDTO;

@Mapper(componentModel = "spring",
nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DetailOrderMapper  {
	@Mapping(target = "orderId", source="id.orderId")
	@Mapping(target = "flowerTypeId", source ="id.typeId")
	@Mapping(target = "saleUnitId", source = "id.saleUnitId")
	@Mapping(target = "quantity", source = "quantity")
	@Mapping(target ="priceAtOrder", source="priceAtOrder")
	@Mapping(target="totalPriceAtOrder", source = "totalPriceAtOrder")
	@Mapping(target = "customerName", source = "idOrder_Detail.customer.nameCustomer")
    @Mapping(target = "orderDate", source = "idOrder_Detail.dateOrder", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "orderStatusId", source = "idOrder_Detail.currentStatus.statusCode")
    @Mapping(target = "orderStatusName", source = "idOrder_Detail.currentStatus.statusName")
    @Mapping(target = "flowerTypeName", source = "idTypeFlowers_Detail.nameFlowerType")
    @Mapping(target = "saleUnitName", source = "idSaleUnit_Detail.nameSaleUnit")
    DetailOrderDTO toDTO(DetailOrder detailOrder);

    // --- Ánh xạ từ DTO sang Entity (DetailOrderDTO -> DetailOrder) ---
	// (Dùng cho save)
	@Mapping(target = "id", ignore = true) // ID composite sẽ được tạo trong service
    @Mapping(target = "idOrder_Detail", ignore = true) // Quan hệ với Order được gán trong service
    @Mapping(target = "idTypeFlowers_Detail", ignore = true) // Quan hệ với FlowerType được gán trong service
    @Mapping(target = "idSaleUnit_Detail", ignore = true) // Quan hệ với SaleUnit được gán trong service
    @Mapping(target = "priceAtOrder", ignore = true) // Giá được tính toán trong service
    @Mapping(target = "totalPriceAtOrder", ignore = true) // Tổng giá được tính toán trong service
    @Mapping(target = "quantity", source = "quantity")
    DetailOrder toEntity(DetailOrderDTO detailOrderDTO);
    
    List<DetailOrderDTO> toDTOList(List<DetailOrder> detailOrderList);
}