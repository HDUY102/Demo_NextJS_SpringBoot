package com.example.demo.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.SellFlowers;
@Repository
public interface SellFlowersRepository extends JpaRepository<SellFlowers, Long> {

}
