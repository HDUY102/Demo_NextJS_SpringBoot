package com.example.services;

import java.util.*;

import com.example.dtos.CustomerDTO;

public interface CustomerService {
	List<CustomerDTO> findAllCustomers();
	CustomerDTO findCusById(Long id);
}
