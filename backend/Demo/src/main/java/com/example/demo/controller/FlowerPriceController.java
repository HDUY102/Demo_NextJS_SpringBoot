package com.example.demo.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.dtos.*;
import com.example.services.FlowerPriceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/api/flowerprice")
@RequiredArgsConstructor
@RestController
public class FlowerPriceController {
	private final FlowerPriceService flowerPriceSer;
	
	@GetMapping
	public ResponseEntity<List<FlowerPriceDTO>> getAllPrice(){
		List<FlowerPriceDTO> entity = flowerPriceSer.findAll();
		return ResponseEntity.ok(entity);
	}
	
	@GetMapping("/{typeId}/{unitId}")
	public ResponseEntity<FlowerPriceDTO> getOnePriceById(
			@PathVariable Long typeId,
			@PathVariable Long unitId){
		return ResponseEntity.ok(flowerPriceSer.findByCompositeId(typeId, unitId));
	}
	
	@GetMapping("/by-type/{typeId}")
	public ResponseEntity<List<FlowerPriceDTO>> getPriceByTypeId(@PathVariable Long typeId){
		return ResponseEntity.ok(flowerPriceSer.findPriceByFlowerTypeId(typeId));
	}
	
	@PostMapping
    public ResponseEntity<FlowerPriceDTO> createDetailOrder(@RequestBody @Valid FlowerPriceDTO detailOrderDTO) {
        return ResponseEntity.ok(flowerPriceSer.save(detailOrderDTO));
    }
    
    @PutMapping("/{typeId}/{unitId}")
    public ResponseEntity<FlowerPriceDTO> updateDetailOrder(
            @PathVariable Long typeId,
            @PathVariable Long unitId,
            @RequestBody FlowerPriceDTO flowerPriceDTO) {
    	FlowerPriceDTO updatedDetailOrder = flowerPriceSer.update(typeId, unitId, flowerPriceDTO);
        return ResponseEntity.ok(updatedDetailOrder);
    }
    
    @DeleteMapping("/{typeId}/{unitId}")
    public ResponseEntity<Void> deleteDetailOrder(
            @PathVariable Long orderId,
            @PathVariable Long unitId) {
    	flowerPriceSer.delete(orderId, unitId);
        return ResponseEntity.noContent().build();
    } 
}
