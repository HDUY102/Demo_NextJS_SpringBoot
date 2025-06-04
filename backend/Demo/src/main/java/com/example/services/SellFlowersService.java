package com.example.services;

import java.util.List;

import com.example.dtos.SellFlowersDTO;

public interface SellFlowersService {
	List<SellFlowersDTO> findAllSellFlowers();
	
}
