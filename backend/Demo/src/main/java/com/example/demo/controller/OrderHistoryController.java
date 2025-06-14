package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.OrderHistory;
import com.example.demo.generics.*;
import com.example.dtos.OrderHistoryDTO;
import com.example.services.OrderHistoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/orderhistory")
@RequiredArgsConstructor
public class OrderHistoryController extends BaseController<OrderHistoryDTO, OrderHistoryDTO> {
	private final OrderHistoryService orderHistoryService;

    @Override
    protected BaseService<OrderHistory, OrderHistoryDTO, OrderHistoryDTO> getService() {
        return orderHistoryService;
    }

    @GetMapping("/by-order/{orderId}")
    public ResponseEntity<List<OrderHistoryDTO>> getHistoryByOrderId(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderHistoryService.findHistoryByOrderId(orderId));
    }
}
