package com.example.demo.respository;

import java.util.List;

import com.example.demo.entity.Customers;
import com.example.demo.generics.BaseRepository;

public interface CustomerRepository extends BaseRepository<Customers>{
	List<Customers> findByNameCustomer(String nameCustomer);
}