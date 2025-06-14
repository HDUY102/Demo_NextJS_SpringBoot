package com.example.services;

import java.util.List;

import org.springframework.stereotype.Service;
import com.example.demo.entity.*;
import com.example.demo.respository.*;
import com.example.dtos.DetailOrderDTO;
import com.example.mapper.DetailOrderMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DetailServiceImpl implements DetailOrderService{
	private final DetailOrderRepository detailOrderRepo;
    private final DetailOrderMapper detailMapper;
    private final OrderRepository orderRepository;
    private final FlowerTypesRepository flowerTypesRepository;
    private final SaleUnitRepository saleUnitsRepository;
	
    @Override
    public List<DetailOrderDTO> findAll() {
        List<DetailOrder> detailOrders = detailOrderRepo.findAll();
        return detailMapper.toDTOList(detailOrders);
    }

    @Override
    public DetailOrderDTO findByCompositeId(Long orderId, Long flowerTypeId, Long saleUnitId) {
        DetailOrderId id = new DetailOrderId(orderId, flowerTypeId, saleUnitId);
        return detailOrderRepo.findById(id)
                .map(detailMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("DetailOrder not found with composite id: " + id.toString()));
    }
    
    @Override
    public List<DetailOrderDTO> findById(Long orderId) {
        List<DetailOrder> details = detailOrderRepo.findById_OrderId(orderId);
        return detailMapper.toDTOList(details);
    }
    
    @Override
    public DetailOrderDTO save(DetailOrderDTO detailOrderDTO) {
        DetailOrder entity = detailMapper.toEntity(detailOrderDTO);

        // 1. Tìm Orders
        Orders order = orderRepository.findById(detailOrderDTO.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + detailOrderDTO.getOrderId()));
        entity.setIdOrder_Detail(order);

        // 2. Tìm FlowerTypes
        FlowerTypes flowerType = flowerTypesRepository.findById(detailOrderDTO.getFlowerTypeId())
                .orElseThrow(() -> new RuntimeException("Flower Type not found with ID: " + detailOrderDTO.getFlowerTypeId()));
        entity.setIdTypeFlowers_Detail(flowerType);

        // 3. Tìm SaleUnits
        SaleUnits saleUnit = saleUnitsRepository.findById(detailOrderDTO.getSaleUnitId())
                .orElseThrow(() -> new RuntimeException("Sale Unit not found with ID: " + detailOrderDTO.getSaleUnitId()));
        entity.setIdSaleUnit_Detail(saleUnit);

        if (entity.getId() == null) {
             entity.setId(new DetailOrderId(detailOrderDTO.getOrderId(),
                                          detailOrderDTO.getFlowerTypeId(),
                                          detailOrderDTO.getSaleUnitId()));
        }

        return detailMapper.toDTO(detailOrderRepo.save(entity));
    }

    @Override
    public DetailOrderDTO update(Long orderId, Long flowerTypeId, Long saleUnitId, DetailOrderDTO detailOrderDTO) {
        // Tạo DetailOrderId từ các tham số của phương thức
        DetailOrderId id = new DetailOrderId(orderId, flowerTypeId, saleUnitId);

        // Tìm Entity hiện có bằng khóa phức hợp
        DetailOrder existingEntity = detailOrderRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("DetailOrder not found with composite id: " + id.toString()));

        // Chỉ các trường dữ liệu như quantity, priceAtOrder, totalPriceAtOrder được cập nhật.
        detailMapper.updateEntityFromDto(detailOrderDTO, existingEntity);

        // Lưu Entity đã cập nhật
        return detailMapper.toDTO(detailOrderRepo.save(existingEntity));
    }

    @Override
    public void delete(Long orderId, Long flowerTypeId, Long saleUnitId) {
        // Tạo DetailOrderId từ các tham số của phương thức
        DetailOrderId id = new DetailOrderId(orderId, flowerTypeId, saleUnitId);

        // Kiểm tra tồn tại
        if (!detailOrderRepo.existsById(id)) {
            throw new RuntimeException("DetailOrder not found with composite id: " + id.toString());
        }
        detailOrderRepo.deleteById(id);
    }
}