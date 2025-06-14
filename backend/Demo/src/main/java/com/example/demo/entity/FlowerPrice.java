package com.example.demo.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.example.demo.validation.Role_Of_BigDecimal;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "flower_prices")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlowerPrice {
	    @EmbeddedId
	    private FlowerPriceId id;

	    @ManyToOne
	    @MapsId("flowerTypeId")
	    @JoinColumn(name = "flower_type_id")
	    private FlowerTypes flowerType;

	    @ManyToOne
	    @MapsId("saleUnitId")
	    @JoinColumn(name = "sale_unit_id")
	    private SaleUnits saleUnit;

	    @Role_Of_BigDecimal
	    @Column(precision = 19, scale = 0)
	    private BigDecimal priceOfUnit;
	    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
	    private Date updatedAt;
}