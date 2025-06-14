package com.example.services;

import java.util.List;
import com.example.dtos.FlowerPriceDTO;

public interface FlowerPriceService {
	List<FlowerPriceDTO> findAll();
	List<FlowerPriceDTO> findPriceByFlowerTypeId(Long flowerTypeId);
	FlowerPriceDTO findByCompositeId(Long typeId, Long saleUnitId);
	FlowerPriceDTO save(FlowerPriceDTO flowerPriceDTO);
	FlowerPriceDTO update(Long flowerTypeId, Long saleUnitId, FlowerPriceDTO flowerPriceDTO);
	void delete(Long typeId, Long saleUnitId);
}
