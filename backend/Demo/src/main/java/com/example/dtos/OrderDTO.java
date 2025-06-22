package com.example.dtos;

import java.math.BigDecimal;
import java.util.*;
import com.example.demo.generics.BaseDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
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
	private String orderHistoryJson;
	private Long customerId;
	private String customerName;
    private Long currentStatusId;
    private List<DetailOrderDTO> details;
}