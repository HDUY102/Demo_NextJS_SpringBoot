package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.dtos.CustomerDTO;
import com.example.repository.CustomerRepository;
import com.example.services.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
	@Autowired
	CustomerService cusService;
	@Autowired
	CustomerRepository cusReposi;
	
	@GetMapping
	public ResponseEntity<List<CustomerDTO>> getAll(){
		return ResponseEntity.ok(cusService.findAllCustomers());
	}
	
//	@GetMapping
//	public ResponseEntity<CustomerDTO> getCusById(@PathVariable Long id ){
//		return ResponseEntity.ok(cusService.findCusById(id));
//	}
}
