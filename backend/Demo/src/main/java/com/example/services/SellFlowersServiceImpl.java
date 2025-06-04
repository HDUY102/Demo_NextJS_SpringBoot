package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.SellFlowers;
import com.example.demo.respository.SellFlowersRepository;
import com.example.dtos.SellFlowersDTO;
import com.example.mapper.SellFlowersMapper;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class SellFlowersServiceImpl implements SellFlowersService{
	@Autowired
	SellFlowersRepository sellFlowersRepo;
	@Autowired
	SellFlowersMapper sellFlowersMap;
	
	@Override
	public List<SellFlowersDTO> findAllSellFlowers() {
		List<SellFlowers> sellFlowers = sellFlowersRepo.findAll();
		return sellFlowersMap.toDTOList(sellFlowers);
	}
}
