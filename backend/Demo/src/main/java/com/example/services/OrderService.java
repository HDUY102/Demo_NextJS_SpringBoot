package com.example.services;

import java.util.List;

import com.example.demo.entity.Orders;
import com.example.demo.generics.BaseService;
import com.example.dtos.OrderDTO;

public interface OrderService extends BaseService<Orders, OrderDTO, OrderDTO>{
	List<OrderDTO> findOrdersByCustomerName(String customerName);
}