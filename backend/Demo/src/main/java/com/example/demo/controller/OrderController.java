package com.example.demo.controller;
import java.util.List;
import java.util.Map;

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
    
    @PostMapping
    public ResponseEntity<List<OrderDTO>> searchOrders(@RequestBody(required = false) Map<String, String> body) {
        String keyword = null;
        if (body != null && body.containsKey("keyword")) {
            keyword = body.get("keyword");
        }
        
        List<OrderDTO> orders;
        if (keyword != null && !keyword.trim().isEmpty()) {
            orders = orderService.searchOrders(keyword);
        } else {
            orders = orderService.findAll();
        }
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/by-namecustomer")
    public ResponseEntity<List<OrderDTO>> getOrdersByCustomerName(@RequestParam(name="customerName") String customerName) {
        List<OrderDTO> orders = orderService.findOrdersByCustomerName(customerName);
        return ResponseEntity.ok(orders);
    }
    
    @PutMapping("/{orderId}/status")
//    public ResponseEntity<OrderDTO> updateOrderStatus( @PathVariable Long orderId, @RequestParam("newStatusId") Long newStatusId,
//            @RequestParam(value = "note", required = false) String note) { // Truyền thông tin vào params URL
    public ResponseEntity<OrderDTO> updateOrderStatus(@PathVariable Long orderId, @RequestBody OrderDTO orderDTO){
//        OrderDTO updateOrder = orderService.updateOrderStatus(orderId, newStatusId, note); // dùng params URL để cập nhật 
    	Long newStatusId = orderDTO.getCurrentStatusId();
    	String orderHistoryJson = orderDTO.getOrderHistoryJson();
    	OrderDTO updateOrder = orderService.updateOrderStatus(orderId, newStatusId, orderHistoryJson);
        return ResponseEntity.ok(updateOrder);
    }
}