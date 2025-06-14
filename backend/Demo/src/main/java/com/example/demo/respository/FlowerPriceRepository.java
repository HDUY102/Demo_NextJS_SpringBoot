package com.example.demo.respository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.FlowerPrice;
import com.example.demo.entity.FlowerPriceId;

public interface FlowerPriceRepository extends JpaRepository<FlowerPrice, FlowerPriceId>{
	List<FlowerPrice> findById_flowerTypeId(Long flowerTypeId);
}