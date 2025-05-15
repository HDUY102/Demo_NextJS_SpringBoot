package com.example.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="customers")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customers {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long idCustomer;
	private String nameCustomer;
	private String phone;
	private String address;
}
