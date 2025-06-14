package com.example.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Customers;
import com.example.demo.generics.BaseServiceImpl;
import com.example.demo.respository.CustomerRepository;
import com.example.dtos.CustomerDTO;
import com.example.mapper.CustomerMapper;

@Service
public class CustomerServiceImpl extends BaseServiceImpl<Customers, CustomerDTO, CustomerDTO>
        implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        super(customerRepository, customerMapper);
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDTO> findByName(String nameCustomer) {
    	return baseMapper.toDtoList(customerRepository.findByNameCustomer(nameCustomer));
    }
}