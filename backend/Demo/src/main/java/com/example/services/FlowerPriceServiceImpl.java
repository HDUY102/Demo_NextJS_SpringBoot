package com.example.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.entity.FlowerPrice;
import com.example.demo.entity.FlowerPriceId;
import com.example.demo.entity.FlowerTypes;
import com.example.demo.entity.SaleUnits;
import com.example.demo.respository.*;
import com.example.dtos.FlowerPriceDTO;
import com.example.mapper.FlowerPriceMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FlowerPriceServiceImpl implements FlowerPriceService{
	private final FlowerPriceRepository flowerPriceRepo;
	private final FlowerPriceMapper flowerPriceMapper;
	private final FlowerTypesRepository flowerTypesRepository;
	private final SaleUnitRepository saleUnitsRepository;
    
	@Override
	public List<FlowerPriceDTO> findAll(){
		List<FlowerPrice> flowerEntity = flowerPriceRepo.findAll();
		return flowerPriceMapper.toDTOList(flowerEntity);
	}
	
	@Override
	public List<FlowerPriceDTO> findPriceByFlowerTypeId(Long idFlower){
		List<FlowerPrice> prices = flowerPriceRepo.findById_flowerTypeId(idFlower);
		return flowerPriceMapper.toDTOList(prices);
	}
	
	@Override
	public FlowerPriceDTO findByCompositeId(Long typeId, Long saleUnitId) {
		FlowerPriceId id = new FlowerPriceId(typeId, saleUnitId);
		return flowerPriceRepo.findById(id).map(flowerPriceMapper::toDTO)
				.orElseThrow(() -> new RuntimeException("DetailOrder not found with composite id: " + id.toString()));
	}
	
	@Override
	public FlowerPriceDTO save(FlowerPriceDTO flowerPriceDTO) {
		FlowerPrice entity = flowerPriceMapper.toEntity(flowerPriceDTO);
		
		FlowerTypes types = flowerTypesRepository.findById(flowerPriceDTO.getFlowerTypeId())
				.orElseThrow(() -> new RuntimeException("Flower Type not found with ID: " + flowerPriceDTO.getFlowerTypeId()));
        entity.setFlowerType(types);
        
        SaleUnits units = saleUnitsRepository.findById(flowerPriceDTO.getSaleUnitId())
                .orElseThrow(() -> new RuntimeException("Sale Unit not found with ID: " + flowerPriceDTO.getSaleUnitId()));
        entity.setSaleUnit(units);
        
        if (entity.getId() == null) {
            entity.setId(new FlowerPriceId(flowerPriceDTO.getFlowerTypeId(), flowerPriceDTO.getSaleUnitId()));
        }

        return flowerPriceMapper.toDTO(flowerPriceRepo.save(entity));
	}
	
	@Override
	public FlowerPriceDTO update(Long flowerTypeId, Long saleUnitId, FlowerPriceDTO flowerPriceDTO) {
		FlowerPriceId id = new FlowerPriceId(flowerTypeId, saleUnitId);

        FlowerPrice existingEntity = flowerPriceRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("FlowerPrice not found with composite id: " + id.toString()));
        flowerPriceMapper.updateEntityFromDto(flowerPriceDTO, existingEntity);

        return flowerPriceMapper.toDTO(flowerPriceRepo.save(existingEntity));
	}
	
	@Override
	public void delete(Long typeId, Long saleUnitId) {
		FlowerPriceId id = new FlowerPriceId(typeId, saleUnitId);
        if (!flowerPriceRepo.existsById(id)) {
            throw new RuntimeException("FlowerPrice not found with composite id: " + id.toString());
        }
        flowerPriceRepo.deleteById(id);
	}
}