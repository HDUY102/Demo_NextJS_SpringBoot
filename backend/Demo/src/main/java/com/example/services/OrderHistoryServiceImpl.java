package com.example.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.entity.OrderHistory;
import com.example.demo.entity.OrderStatus;
import com.example.demo.entity.Orders;
import com.example.demo.generics.BaseServiceImpl;
import com.example.demo.respository.*;
import com.example.dtos.OrderHistoryDTO;
import com.example.mapper.OrderHistoryMapper;

@Service
public class OrderHistoryServiceImpl extends BaseServiceImpl<OrderHistory, OrderHistoryDTO, OrderHistoryDTO>
	implements OrderHistoryService{
	private final OrderHistoryRepository orderHistoryRepo;
    private final OrderRepository orderRepository;
    private final OrderStatusRepository orderStatusRepository;
    
    public OrderHistoryServiceImpl(OrderHistoryRepository orderHistoryRepo, OrderHistoryMapper orderHistoryMapper,
            OrderRepository orderRepository,
            OrderStatusRepository orderStatusRepository) {
        super(orderHistoryRepo, orderHistoryMapper);
        this.orderHistoryRepo = orderHistoryRepo;
        this.orderRepository = orderRepository;
        this.orderStatusRepository = orderStatusRepository;
    }
    
    @Override
    public OrderHistoryDTO save(OrderHistoryDTO dto) {
        OrderHistory entity = baseMapper.toEntity(dto);

        Orders order = orderRepository.findById(dto.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + dto.getOrderId()));
        entity.setIdOrder_History(order);

        OrderStatus status = orderStatusRepository.findById(dto.getStatusId())
                .orElseThrow(() -> new RuntimeException("Order Status not found with ID: " + dto.getStatusId()));
        entity.setIdStatus_History(status);

        return baseMapper.toDTO(baseRepository.save(entity));
    }
    
    @Override
    public OrderHistoryDTO update(Long id, OrderHistoryDTO dto) {
        OrderHistory existingEntity = baseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrderHistory not found with ID: " + id));

        baseMapper.updateEntityFromDto(dto, existingEntity);

        if (dto.getOrderId() != null && !dto.getOrderId().equals(existingEntity.getIdOrder_History().getId())) {
            Orders newOrder = orderRepository.findById(dto.getOrderId())
                               .orElseThrow(() -> new RuntimeException("Order not found with ID: " + dto.getOrderId()));
            existingEntity.setIdOrder_History(newOrder);
        }
        if (dto.getStatusId() != null && !dto.getStatusId().equals(existingEntity.getIdStatus_History().getId())) {
            OrderStatus newStatus = orderStatusRepository.findById(dto.getStatusId())
                                      .orElseThrow(() -> new RuntimeException("Order Status not found with ID: " + dto.getStatusId()));
            existingEntity.setIdStatus_History(newStatus);
        }

        return baseMapper.toDTO(baseRepository.save(existingEntity));
    }

    @Override
    public List<OrderHistoryDTO> findHistoryByOrderId(Long orderId) {
        return baseMapper.toDtoList(orderHistoryRepo.findByIdOrder_History_Id(orderId));
    }
}