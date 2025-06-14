package com.example.demo.respository;

import java.util.List;

import com.example.demo.entity.OrderHistory;
import com.example.demo.generics.BaseRepository;

public interface OrderHistoryRepository extends BaseRepository<OrderHistory>{
	List<OrderHistory> findByIdOrder_History_Id(Long orderId);
}
