package com.example.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Customers;
import com.example.demo.entity.OrderStatus;
import com.example.demo.entity.Orders;
import com.example.demo.generics.BaseServiceImpl;
import com.example.demo.respository.CustomerRepository;
import com.example.demo.respository.OrderRepository;
import com.example.demo.respository.OrderStatusRepository;
import com.example.dtos.OrderDTO;
import com.example.mapper.OrderMapper;

@Service
public class OrderServiceImpl extends BaseServiceImpl<Orders, OrderDTO, OrderDTO>
		implements OrderService{
	private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final CustomerRepository customerRepository;
    private final OrderStatusRepository orderStatusRepository;

    public OrderServiceImpl(OrderRepository orderRepository,
                            OrderMapper orderMapper,
                            CustomerRepository customerRepository,
                            OrderStatusRepository orderStatusRepository) {
        super(orderRepository, orderMapper);
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.customerRepository = customerRepository;
        this.orderStatusRepository = orderStatusRepository;
    }

    @Override
    public OrderDTO save(OrderDTO dto) {
        Orders entity = baseMapper.toEntity(dto);

        if (dto.getCustomerId() != null) {
            Customers customer = customerRepository.findById(dto.getCustomerId())
                    .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + dto.getCustomerId()));
            entity.setCustomer(customer);
        } else {
            throw new RuntimeException("Customer ID is required for a new Order.");
        }

        if (dto.getCurrentStatusId() != null) {
            OrderStatus orderStatus = orderStatusRepository.findById(dto.getCurrentStatusId())
                    .orElseThrow(() -> new RuntimeException("Order Status not found with ID: " + dto.getCurrentStatusId()));
            entity.setCurrentStatus(orderStatus);
        } else {
            throw new RuntimeException("Current Status ID is required for a new Order.");
        }

        return baseMapper.toDTO(baseRepository.save(entity));
    }

    @Override
    public OrderDTO update(Long id, OrderDTO dto) {
        Orders existingEntity = baseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + id));

        baseMapper.updateEntityFromDto(dto, existingEntity);

        if (dto.getCustomerId() != null &&
            (existingEntity.getCustomer() == null || !dto.getCustomerId().equals(existingEntity.getCustomer().getId()))) {
            Customers newCustomer = customerRepository.findById(dto.getCustomerId())
                    .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + dto.getCustomerId()));
            existingEntity.setCustomer(newCustomer);
        }

        if (dto.getCurrentStatusId() != null &&
            (existingEntity.getCurrentStatus() == null || !dto.getCurrentStatusId().equals(existingEntity.getCurrentStatus().getId()))) {
            OrderStatus newStatus = orderStatusRepository.findById(dto.getCurrentStatusId())
                    .orElseThrow(() -> new RuntimeException("Order Status not found with ID: " + dto.getCurrentStatusId()));
            existingEntity.setCurrentStatus(newStatus);
        }

        return baseMapper.toDTO(baseRepository.save(existingEntity));
    }

    // Tìm Order = name Customer
     @Override
     public List<OrderDTO> findOrdersByCustomerName(String customerName) {
         return orderMapper.toDtoList(orderRepository.findByCustomerName(customerName));
     }
}