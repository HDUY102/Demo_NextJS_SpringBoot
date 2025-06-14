package com.example.demo.entity;

import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.Data;
import lombok.*;

@Entity
@Table(name="orderdetail")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class DetailOrder {
	@EmbeddedId
	private DetailOrderId id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("orderId")
	@JoinColumn(name = "order_id")
	private Orders idOrder_Detail;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("typeId")
	@JoinColumn(name = "type_id")
	private FlowerTypes idTypeFlowers_Detail;

    @ManyToOne(fetch = FetchType.LAZY)
	@MapsId("saleUnitId")
    @JoinColumn(name = "sale_unit_id")
    private SaleUnits idSaleUnit_Detail;
    
	private Integer quantity;
	@Column(precision = 19, scale = 0)
	private BigDecimal priceAtOrder;
	@Column(precision = 19, scale = 0)
	private BigDecimal totalPriceAtOrder;
}