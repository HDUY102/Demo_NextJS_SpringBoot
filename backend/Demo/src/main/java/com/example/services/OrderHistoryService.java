package com.example.services;

import java.util.List;

import com.example.demo.entity.OrderHistory;
import com.example.demo.generics.BaseService;
import com.example.dtos.OrderHistoryDTO;

public interface OrderHistoryService extends BaseService<OrderHistory, OrderHistoryDTO, OrderHistoryDTO> {
	List<OrderHistoryDTO> findHistoryByOrderId(Long orderId);
}
