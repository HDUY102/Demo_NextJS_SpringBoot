package com.example.dtos;

import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlowerPriceDTO {
	private Long flowerTypeId;
	private Long saleUnitId;
	
	private BigDecimal priceOfUnit;
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
	private Date updatedAt;
	
	private String flowerTypeName;
	private String saleUnitName;
}