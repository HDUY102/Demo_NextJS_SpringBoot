package com.example.demo.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Orders;

public interface UserRespo extends JpaRepository<Orders, Long>{

}
