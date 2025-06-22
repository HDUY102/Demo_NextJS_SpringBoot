package com.example.dtos;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetailOrderDTO{
	private Long orderId;
    private Long flowerTypeId;
    private Long saleUnitId;

    private Integer quantity;
    private BigDecimal priceAtOrder;
    private BigDecimal totalPriceAtOrder;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date orderDate;
    
    private String customerName;
    private String orderStatusId;
    private String orderStatusName;
    private String flowerTypeName;
    private String saleUnitName;
}