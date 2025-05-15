package com.example.dtos;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
public class CustomerDTO {
	private Long idCustomer;
	@NotBlank(message="Name is mandatory")
	@Size(min=2,max=50,message="Name must be between 2 and 50 characters")
	private String nameCustomer;
	private String address;
	private String phone;
}
