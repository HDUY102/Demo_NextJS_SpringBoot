package com.example.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SellFlowersDTO {
	private Long idFlower;
	@NotBlank(message="Name is mandatory")
	@Size(min=2, max=50, message="Name must be between 2 and 50 characters")
	private String nameFlowers;
	private Long incomeInMonth;
	private Long month;
}
