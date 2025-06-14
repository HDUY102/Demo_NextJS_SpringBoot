package com.example.demo.controller;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.Orders;
import com.example.demo.generics.BaseController;
import com.example.demo.generics.BaseService;
import com.example.dtos.OrderDTO;
import com.example.services.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController extends BaseController<OrderDTO, OrderDTO> {
	private final OrderService orderService;

    @Override
    protected BaseService<Orders, OrderDTO, OrderDTO> getService() {
        return orderService;
    }

    @GetMapping("/by-namecustomer")
    public ResponseEntity<List<OrderDTO>> getOrdersByCustomerName(@RequestParam(name="customerName") String customerName) {
        List<OrderDTO> orders = orderService.findOrdersByCustomerName(customerName);
        return ResponseEntity.ok(orders);
    }
}
