package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Sellflowers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SellFlowers {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idFlower;
	private String nameFlowers;
	private Long incomeInMonth;
	private Long month;	
}
