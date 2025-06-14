package com.example.demo.entity;

import com.example.demo.generics.BaseEntity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.*;

@Entity
@Table(name="flowertypes")
@NoArgsConstructor
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FlowerTypes extends BaseEntity{
	private static final long serialVersionUID = 1L;
	private String nameFlowerType;
}