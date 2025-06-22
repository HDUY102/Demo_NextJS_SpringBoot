package com.example.demo.respository;

import java.util.Optional;

import com.example.demo.entity.OrderStatus;
import com.example.demo.generics.BaseRepository;

public interface OrderStatusRepository extends BaseRepository<OrderStatus>{
	Optional<OrderStatus> findByStatusCode(String statusCode);
}
