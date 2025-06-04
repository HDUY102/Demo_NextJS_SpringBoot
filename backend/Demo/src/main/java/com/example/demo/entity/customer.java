package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@Data
public class customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCustomer;
	private String nameCustomer;
	private String address;
	
//	public customer() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//	public customer(Long idCustomer, String nameCustomer, String address) {
//		super();
//		this.idCustomer = idCustomer;
//		this.nameCustomer = nameCustomer;
//		this.address = address;
//	}
//	public Long getIdCustomer() {
//		return idCustomer;
//	}
//	public void setIdCustomer(Long idCustomer) {
//		this.idCustomer = idCustomer;
//	}
//	public String getNameCustomer() {
//		return nameCustomer;
//	}
//	public void setNameCustomer(String nameCustomer) {
//		this.nameCustomer = nameCustomer;
//	}
//	public String getAddress() {
//		return address;
//	}
//	public void setAddress(String address) {
//		this.address = address;
//	}
	
	
}
