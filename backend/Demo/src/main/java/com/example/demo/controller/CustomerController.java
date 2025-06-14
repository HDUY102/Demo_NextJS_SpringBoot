package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Customers;
import com.example.demo.generics.BaseController;
import com.example.demo.generics.BaseService;
import com.example.dtos.CustomerDTO;
import com.example.services.CustomerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController extends BaseController<CustomerDTO, CustomerDTO> {

    private final CustomerService customerService;

    @Override
    protected BaseService<Customers, CustomerDTO, CustomerDTO> getService() {
        return customerService;
    }

    @GetMapping("/search")
    public ResponseEntity<List<CustomerDTO>> searchByName(@RequestParam(name="name", required = false) String nameCustomer) {
        return ResponseEntity.ok(customerService.findByName(nameCustomer));
    }
}