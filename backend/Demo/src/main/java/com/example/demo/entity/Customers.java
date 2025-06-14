package com.example.demo.entity;

import com.example.demo.generics.BaseEntity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customers")
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class Customers extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private String nameCustomer;
	private String address;
	private String phoneNumber;
}