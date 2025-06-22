package com.example.demo.respository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.Orders;
import com.example.demo.generics.BaseRepository;

@Repository
public interface OrderRepository extends BaseRepository<Orders>{
	List<Orders> findByCustomer_NameCustomer(String customerName);
}