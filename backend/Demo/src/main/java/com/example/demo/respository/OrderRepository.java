package com.example.demo.respository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Orders;
import com.example.demo.generics.BaseRepository;

@Repository
public interface OrderRepository extends BaseRepository<Orders>{
	List<Orders> findByCustomer_NameCustomer(String customerName);
	@Query("SELECT o FROM Orders o WHERE " +
		       "LOWER(o.customer.nameCustomer) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
		       "CAST(FUNCTION('DATE', o.dateOrder) AS string) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
		       "CAST(o.totalAmount AS string) LIKE LOWER(CONCAT('%', :keyword, '%'))")
		List<Orders> searchOrders(@Param("keyword") String keyword);
}