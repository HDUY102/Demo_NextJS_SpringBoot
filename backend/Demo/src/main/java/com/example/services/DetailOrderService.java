package com.example.services;

import java.util.List;

import com.example.dtos.DetailOrderDTO;

public interface DetailOrderService{
	List<DetailOrderDTO> findAll();
	DetailOrderDTO findByCompositeId(Long orderId, Long typeId, Long saleUnitId);
	List<DetailOrderDTO> findById(Long orderId);
//	DetailOrderDTO save(DetailOrderDTO categoryDTO);
//	DetailOrderDTO update(Long orderId, Long typeId, Long saleUnitId, DetailOrderDTO detailOrderDTO);
//    void delete(Long orderId, Long typeId, Long saleUnitId);
}