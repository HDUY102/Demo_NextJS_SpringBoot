package com.example.demo.entity;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.*;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailOrderId implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long orderId;
    private Long typeId;
    private Long saleUnitId;
}