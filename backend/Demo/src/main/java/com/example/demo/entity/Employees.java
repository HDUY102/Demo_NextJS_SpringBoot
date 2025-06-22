package com.example.demo.entity;

import com.example.demo.generics.BaseEntity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Employees extends BaseEntity{
	private static final long serialVersionUID = 1L;
	private String nameEmployee;
	private String phoneEmployee;
	private String addressEmployee;
	private String role;
}