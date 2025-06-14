package com.example.demo.entity;

import com.example.demo.generics.BaseEntity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "orderstatus")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OrderStatus extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private String statusCode;
	private String statusName;
	private String descriptionStatus;
	private Boolean canCancel;
}