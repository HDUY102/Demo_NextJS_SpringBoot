package com.example.demo.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.DetailOrder;
import com.example.demo.entity.DetailOrderId;

@Repository
public interface DetailOrderRepository extends JpaRepository<DetailOrder, DetailOrderId>{
	List<DetailOrder> findById_OrderId(Long orderId);
}