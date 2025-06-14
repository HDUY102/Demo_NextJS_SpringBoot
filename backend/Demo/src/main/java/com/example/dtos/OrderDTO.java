package com.example.dtos;

import java.math.BigDecimal;
import java.util.Date;
import com.example.demo.generics.BaseDTO;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class OrderDTO extends BaseDTO{
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dateOrder;
	private Boolean isPaid;
	private BigDecimal totalAmount;
	private Long customerId;
    private Long currentStatusId;
}