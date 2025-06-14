package com.example.services;

import java.util.List;

import com.example.demo.entity.Customers;
import com.example.demo.generics.BaseService;
import com.example.dtos.CustomerDTO;

public interface CustomerService extends BaseService<Customers, CustomerDTO, CustomerDTO> {
    List<CustomerDTO> findByName(String name);
}
