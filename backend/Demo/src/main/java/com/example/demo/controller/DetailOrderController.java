package com.example.demo.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.dtos.DetailOrderDTO;
import com.example.services.DetailOrderService;
//import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/detailorder")
@RequiredArgsConstructor
public class DetailOrderController {
    private final DetailOrderService detailOrderService;
    
    @GetMapping
    public ResponseEntity<List<DetailOrderDTO>> getAllDetailOrders() {
        List<DetailOrderDTO> detailOrders = detailOrderService.findAll();
        return ResponseEntity.ok(detailOrders);
    }
    
    @GetMapping("/{orderId}/{flowerTypeId}/{saleUnitId}")
    public ResponseEntity<DetailOrderDTO> getDetailOrderById(
            @PathVariable Long orderId,
            @PathVariable Long flowerTypeId,
            @PathVariable Long saleUnitId) {
        DetailOrderDTO detailOrder = detailOrderService.findByCompositeId(orderId, flowerTypeId, saleUnitId);
        return ResponseEntity.ok(detailOrder);
    }
    
    @GetMapping("/by-order/{orderId}")
    public ResponseEntity<List<DetailOrderDTO>> getDetailsByOrderId(@PathVariable Long orderId) {
        List<DetailOrderDTO> details = detailOrderService.findById(orderId);
        return ResponseEntity.ok(details);
    }
    
//    @PostMapping
//    public ResponseEntity<DetailOrderDTO> createDetailOrder(@RequestBody @Valid DetailOrderDTO detailOrderDTO) {
//        return ResponseEntity.ok(detailOrderService.save(detailOrderDTO));
//    }
//    
//    @PutMapping("/{orderId}/{flowerTypeId}/{saleUnitId}")
//    public ResponseEntity<DetailOrderDTO> updateDetailOrder(
//            @PathVariable Long orderId,
//            @PathVariable Long flowerTypeId,
//            @PathVariable Long saleUnitId,
//            @RequestBody DetailOrderDTO detailOrderDTO) {
//        DetailOrderDTO updatedDetailOrder = detailOrderService.update(orderId, flowerTypeId, saleUnitId, detailOrderDTO);
//        return ResponseEntity.ok(updatedDetailOrder);
//    }
//    
//    @DeleteMapping("/{orderId}/{flowerTypeId}/{saleUnitId}")
//    public ResponseEntity<Void> deleteDetailOrder(
//            @PathVariable Long orderId,
//            @PathVariable Long flowerTypeId,
//            @PathVariable Long saleUnitId) {
//        detailOrderService.delete(orderId, flowerTypeId, saleUnitId);
//        return ResponseEntity.noContent().build();
//    }    
}