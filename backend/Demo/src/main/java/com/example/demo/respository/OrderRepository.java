package com.example.demo.respository;

import java.util.List;
import com.example.demo.entity.Orders;
import com.example.demo.generics.BaseRepository;

public interface OrderRepository extends BaseRepository<Orders>{
	List<Orders> findByCustomerName(String customerName);
}