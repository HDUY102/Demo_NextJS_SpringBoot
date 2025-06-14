package com.example.demo.entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlowerPriceId implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long flowerTypeId;
	private Long saleUnitId;
}