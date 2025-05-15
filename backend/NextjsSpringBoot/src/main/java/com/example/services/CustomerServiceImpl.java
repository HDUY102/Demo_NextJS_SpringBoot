package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dtos.CustomerDTO;
import com.example.entity.Customers;
import com.example.mapper.CustomerMapper;
import com.example.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	CustomerRepository cusReposi;
	@Autowired
    CustomerMapper customerMapper;
	
	@Override
	public List<CustomerDTO> findAllCustomers() {
		List<Customers> customer = cusReposi.findAll();
		return customerMapper.toDTOs(customer);
	}
	
	@Override
	public CustomerDTO findCusById(Long id) {
		Customers custom = cusReposi.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
        return customerMapper.toDTO(custom);
	}
}
