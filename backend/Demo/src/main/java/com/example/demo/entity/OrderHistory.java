package com.example.demo.entity;
import java.util.Date;
import com.example.demo.generics.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "orderHistory")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OrderHistory extends BaseEntity{
	private static final long serialVersionUID = 1L;
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Orders idOrder_History;
	
	@ManyToOne
	@JoinColumn(name = "status_id")
	private OrderStatus idStatus_History;
	
	private Date changedAt;
	private String changedBy;
	private String note;
}