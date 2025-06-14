package com.example.demo.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.DetailOrder;
import com.example.demo.entity.DetailOrderId;

public interface DetailOrderRepository extends JpaRepository<DetailOrder, DetailOrderId>{
	List<DetailOrder> findById_OrderId(Long orderId);
}