package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Customers;

@Repository
public interface CustomerRepository extends JpaRepository<Customers, Long>{
	
}