package com.example.demo.entity;

import jakarta.persistence.Entity;

import com.example.demo.generics.BaseEntity;

import jakarta.persistence.*;
import lombok.*;
import lombok.Data;

@Entity
@Table(name="saleunits")
@NoArgsConstructor
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SaleUnits  extends BaseEntity {
	private static final long serialVersionUID = 1L;
    private String nameSaleUnit;
}