package com.example.demo.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.example.demo.generics.BaseEntity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name="orders")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class Orders extends BaseEntity{
	private static final long serialVersionUID = 1L;
	@CreationTimestamp
	private Date dateOrder;
	private Boolean isPaid;
	@Column(precision = 19, scale = 0)
	private BigDecimal totalAmount;
	@Column(columnDefinition = "json")
    private String orderHistoryJson;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	private Customers customer;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "current_status_id")
	private OrderStatus currentStatus;
	
	@OneToMany(mappedBy = "idOrder_Detail", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetailOrder> details;
}