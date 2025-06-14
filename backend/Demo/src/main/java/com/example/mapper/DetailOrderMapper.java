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
    @Mapping(target = "orderStatusName", source = "idOrder_Detail.currentStatus.statusName")
    @Mapping(target = "flowerTypeName", source = "idTypeFlowers_Detail.nameFlowerType")
    @Mapping(target = "saleUnitName", source = "idSaleUnit_Detail.nameSaleUnit")
    DetailOrderDTO toDTO(DetailOrder detailOrder);

    // --- Ánh xạ từ DTO sang Entity (DetailOrderDTO -> DetailOrder) ---
	// (Dùng cho save)
    @Mapping(target = "id.orderId", source = "orderId")
    @Mapping(target = "id.typeId", source = "flowerTypeId") // flowerTypeId trong DTO -> typeId trong DetailOrderId
    @Mapping(target = "id.saleUnitId", source = "saleUnitId") // saleUnitId trong DTO -> saleUnitId trong DetailOrderId
    @Mapping(target = "idOrder_Detail", ignore = true)
    @Mapping(target = "idTypeFlowers_Detail", ignore = true)
    @Mapping(target = "idSaleUnit_Detail", ignore = true)
    @Mapping(target = "quantity", source = "quantity")
    @Mapping(target = "priceAtOrder", source = "priceAtOrder")
    @Mapping(target = "totalPriceAtOrder", source = "totalPriceAtOrder")
    DetailOrder toEntity(DetailOrderDTO detailOrderDTO);
    
    List<DetailOrderDTO> toDTOList(List<DetailOrder> detailOrderList);

    // --- Phương thức cập nhật Entity từ DTO (DetailOrderDTO -> DetailOrder) ---
    // (Dùng cho update)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "idOrder_Detail", ignore = true)
    @Mapping(target = "idTypeFlowers_Detail", ignore = true)
    @Mapping(target = "idSaleUnit_Detail", ignore = true)
    @Mapping(target = "quantity", source = "quantity")
    @Mapping(target = "priceAtOrder", source = "priceAtOrder")
    @Mapping(target = "totalPriceAtOrder", source = "totalPriceAtOrder")
    void updateEntityFromDto(DetailOrderDTO dto, @MappingTarget DetailOrder entity);
}