package com.example.services;

import java.util.List;

import com.example.demo.entity.Orders;
import com.example.demo.generics.BaseService;
import com.example.dtos.OrderDTO;
import org.springframework.data.domain.*;
public interface OrderService extends BaseService<Orders, OrderDTO, OrderDTO>{
	List<OrderDTO> findOrdersByCustomerName(String customerName);
	OrderDTO updateOrderStatus(Long orderId, Long newStatusId, String note);
	List<OrderDTO> searchOrders(String keyword);
	
	//Pagination
	Page<OrderDTO> findAllPagination(Pageable pageable);
	Page<OrderDTO> searchOrdersPagination(String keyword, Pageable pageable);
}