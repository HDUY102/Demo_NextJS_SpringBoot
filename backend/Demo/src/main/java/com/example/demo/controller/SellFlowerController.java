package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.respository.SellFlowersRepository;
import com.example.dtos.SellFlowersDTO;
import com.example.services.SellFlowersService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/sellflowers")
@RestController
@RequiredArgsConstructor
public class SellFlowerController {
	@Autowired
	SellFlowersService sellFlowersService;
	@Autowired
	SellFlowersRepository sellFlowersRepository;
	
	@GetMapping
	public ResponseEntity<List<SellFlowersDTO>> getAll(){
		return ResponseEntity.ok(sellFlowersService.findAllSellFlowers());
	}
}
