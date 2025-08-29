package com.example.services;
import java.math.BigDecimal;
import java.util.*;
import org.springframework.stereotype.Service;
import com.example.demo.entity.*;
import com.example.demo.generics.BaseServiceImpl;
import com.example.demo.respository.*;
import com.example.dtos.*;
import com.example.mapper.OrderMapper;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.type.*;
import com.fasterxml.jackson.databind.*;
import org.springframework.data.domain.Sort;
import jakarta.transaction.Transactional;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
class StatusHistoryEntry {
    private Date changedAt;
    private Long oldStatusId;
    private String oldStatusCode;
    private Long newStatusId;
    private String newStatusCode;
    private String changedBy;
    private String note;
}

@Service
public class OrderServiceImpl extends BaseServiceImpl<Orders, OrderDTO, OrderDTO>
		implements OrderService{
	private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final CustomerRepository customerRepository;
    private final OrderStatusRepository orderStatusRepository;
    private final ObjectMapper objectMapper;
    private final FlowerTypesRepository flowerTypesRepository;
    private final SaleUnitRepository saleUnitsRepository;
    private final FlowerPriceRepository flowerPriceRepository;
    
    public OrderServiceImpl(OrderRepository orderRepository,OrderMapper orderMapper, SaleUnitRepository saleUnitsRepository,
                            CustomerRepository customerRepository, FlowerTypesRepository flowerTypesRepository,
             OrderStatusRepository orderStatusRepository, ObjectMapper objectMapper, FlowerPriceRepository flowerPriceRepository) {
        super(orderRepository, orderMapper);
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.customerRepository = customerRepository;
        this.orderStatusRepository = orderStatusRepository;
        this.objectMapper = objectMapper;
        this.saleUnitsRepository = saleUnitsRepository;
        this.flowerTypesRepository = flowerTypesRepository;
        this.flowerPriceRepository = flowerPriceRepository;
    }
    
    // Search Order Table
    @Override
    public List<OrderDTO> searchOrders(String keyword){
    	if (keyword == null || keyword.trim().isEmpty()) {
            return findAll();
        }
        List<Orders> orders = orderRepository.searchOrders(keyword);
        return orderMapper.toDtoList(orders);
    }
    
    // Sort by Date
    @Override
    public List<OrderDTO> findAll() {
        Sort sort = Sort.by(Sort.Direction.DESC, "dateOrder"); // Default sort by date desc
        return orderMapper.toDtoList(orderRepository.findAll(sort));
    }

    @Override
    @Transactional
    public OrderDTO save(OrderDTO dto) {
        Orders entity = baseMapper.toEntity(dto);
        
        if (dto.getCustomerId() == null) { // tìm customer
            throw new RuntimeException("Customer ID is required for a new Order.");
        }
        Customers customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + dto.getCustomerId()));
        entity.setCustomer(customer);

        // Đơn hàng ONLINE: Trạng thái ban đầu: NEW
        OrderStatus initialStatus = orderStatusRepository.findByStatusCode("NEW")
        		.orElseThrow(() -> new RuntimeException("NEW status not found."));
        String initialNote = "Đơn hàng mới đã được tạo.";
        entity.setCurrentStatus(initialStatus);
        
        List<StatusHistoryEntry> historyEntries = new ArrayList<>();
        // Trạng thái ban đầu là null
        historyEntries.add(createHistoryEntry(null, initialStatus, getLoggedInUser(), initialNote));
        try {
            entity.setOrderHistoryJson(objectMapper.writeValueAsString(historyEntries));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting order history to JSON on save.", e);
        }
        
        //  Xử lý DetailOrder
        BigDecimal calculatedTotalAmount = BigDecimal.ZERO;
        List<DetailOrder> details = new ArrayList<>();

        if (dto.getDetails() == null || dto.getDetails().isEmpty()) {
            throw new IllegalArgumentException("Order phải chứa ít nhất 1 item.");
        }

        for (DetailOrderDTO detailDto : dto.getDetails()) {
            if (detailDto.getQuantity() == null || detailDto.getQuantity() <= 0) {
                throw new IllegalArgumentException("Số lượng phải lớn hơn 0.");
            }
            if (detailDto.getFlowerTypeId() == null) {
                 throw new IllegalArgumentException("Không tìm thấy loại hoa ID: " + detailDto.getFlowerTypeId());
            }
            if (detailDto.getSaleUnitId() == null) {
                throw new IllegalArgumentException("Không tìm thấy đơn vị bán ID: " + detailDto.getSaleUnitId());
            }

            // Retrieve related entities
            FlowerTypes flowerType = flowerTypesRepository.findById(detailDto.getFlowerTypeId())
                    .orElseThrow(() -> new RuntimeException("Flower Type not found with ID: " + detailDto.getFlowerTypeId()));
            SaleUnits saleUnit = saleUnitsRepository.findById(detailDto.getSaleUnitId())
                    .orElseThrow(() -> new RuntimeException("Sale Unit not found with ID: " + detailDto.getSaleUnitId()));
            FlowerPrice flowerPrice = flowerPriceRepository.findById(new FlowerPriceId(flowerType.getId(), saleUnit.getId()))
                    .orElseThrow(() -> new RuntimeException("Price not found for Flower Type: " + flowerType.getNameFlowerType() + " and Sale Unit: " + saleUnit.getNameSaleUnit()));

            BigDecimal priceAtOrder = flowerPrice.getPriceOfUnit();
            BigDecimal totalPriceAtOrder = priceAtOrder.multiply(BigDecimal.valueOf(detailDto.getQuantity()));

            // Create DetailOrder entity
            DetailOrder detailEntity = new DetailOrder();
            detailEntity.setId(new DetailOrderId(null, flowerType.getId(), saleUnit.getId())); 

            detailEntity.setIdOrder_Detail(entity); 
            detailEntity.setIdTypeFlowers_Detail(flowerType);
            detailEntity.setIdSaleUnit_Detail(saleUnit);
            detailEntity.setQuantity(detailDto.getQuantity());
            detailEntity.setPriceAtOrder(priceAtOrder);
            detailEntity.setTotalPriceAtOrder(totalPriceAtOrder);

            details.add(detailEntity);
            calculatedTotalAmount = calculatedTotalAmount.add(totalPriceAtOrder);
        }
        entity.setDetails(details);
        entity.setTotalAmount(calculatedTotalAmount);
        
        return baseMapper.toDTO(baseRepository.save(entity));
    }

    @Override
    @Transactional
    public OrderDTO update(Long id, OrderDTO dto) {
    	Orders existingEntity = baseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + id));

    	if (dto.getCustomerId() != null && !dto.getCustomerId().equals(existingEntity.getCustomer().getId())) {
            throw new IllegalArgumentException("Cannot change Customer ID");
        }
    	
    	OrderStatus oldStatus = existingEntity.getCurrentStatus();
        baseMapper.updateEntityFromDto(dto, existingEntity);
        OrderStatus newStatus = null;
        String note = "";
        boolean statusChanged = false;

        if (dto.getCurrentStatusId() != null && (oldStatus == null || !dto.getCurrentStatusId().equals(oldStatus.getId()))) {
            newStatus = orderStatusRepository.findById(dto.getCurrentStatusId())
                    .orElseThrow(() -> new RuntimeException("Order Status not found with ID: " + dto.getCurrentStatusId()));
            
            note = "Trạng thái đơn hàng được cập nhật từ " + (oldStatus != null ? oldStatus.getStatusName() : "N/A") + " sang " + newStatus.getStatusName() + ".";
            
            existingEntity.setCurrentStatus(newStatus);
            statusChanged = true;
        }

        if (statusChanged) {
            addHistoryEntryToJson(existingEntity, oldStatus, newStatus, getLoggedInUser(), note);
        }

        return baseMapper.toDTO(baseRepository.save(existingEntity));
    }

    // Tìm Order = name Customer
     @Override
     public List<OrderDTO> findOrdersByCustomerName(String customerName) {
         return orderMapper.toDtoList(orderRepository.findByCustomer_NameCustomer(customerName));
     }
     
     private StatusHistoryEntry createHistoryEntry(OrderStatus oldStatus, OrderStatus newStatus, String changedBy, String note) {
         StatusHistoryEntry entry = new StatusHistoryEntry();
         entry.setChangedAt(new Date());
         entry.setOldStatusId(oldStatus != null ? oldStatus.getId() : null);
         entry.setOldStatusCode(oldStatus != null ? oldStatus.getStatusCode() : null);
         entry.setNewStatusId(newStatus != null ? newStatus.getId() : null);
         entry.setNewStatusCode(newStatus != null ? newStatus.getStatusCode() : null);
         entry.setChangedBy(changedBy);
         entry.setNote(note);
         return entry;
     }
     
     private void addHistoryEntryToJson(Orders order, OrderStatus oldStatus, OrderStatus newStatus, String changedBy, String note) {
         List<StatusHistoryEntry> historyEntries = new ArrayList<>();
         
         // Bước 1: Đọc chuỗi JSON hiện có từ entity
         String existingJson = order.getOrderHistoryJson();
         if (existingJson != null && !existingJson.isEmpty()) {
             try {
                 // Bước 2: Giải tuần tự hóa chuỗi JSON thành List<StatusHistoryEntry>
                 historyEntries = objectMapper.readValue(existingJson, new TypeReference<List<StatusHistoryEntry>>() {});
             } catch (JsonProcessingException e) {
                 System.err.println("Error parsing existing order history JSON for Order ID " + order.getId() + ": " + e.getMessage());
             }
         }
         
         // Bước 3: Thêm mục lịch sử mới vào danh sách
         historyEntries.add(createHistoryEntry(oldStatus, newStatus, changedBy, note));
         
         // Bước 4: Tuần tự hóa danh sách trở lại thành chuỗi JSON
         try {
             order.setOrderHistoryJson(objectMapper.writeValueAsString(historyEntries));
         } catch (JsonProcessingException e) {
             throw new RuntimeException("Error converting order history to JSON for update.", e);
         }
     }

     // Phương thức giả định để lấy tên người dùng hiện tại
     private String getLoggedInUser() {
         // Ví dụ: return SecurityContextHolder.getContext().getAuthentication().getName();
         return "System"; // Mặc định là "System" nếu không có thông tin người dùng đăng nhập
     }
     
     @Override
     @Transactional
     public OrderDTO updateOrderStatus(Long orderId, Long newStatusId, String note) {
         Orders order = orderRepository.findById(orderId)
                 .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));

         OrderStatus oldStatus = order.getCurrentStatus();
         OrderStatus newStatus = orderStatusRepository.findById(newStatusId)
                 .orElseThrow(() -> new RuntimeException("New Order Status not found with ID: " + newStatusId));

         if (oldStatus.getId().equals(newStatus.getId())) {
             throw new IllegalArgumentException("Order is already in the target status.");
         }

         order.setCurrentStatus(newStatus);
         
         // Ghi lịch sử thay đổi trạng thái
         String historyNote = note != null && !note.isEmpty()
                              ? note
                              : "Trạng thái đơn hàng được cập nhật từ " + oldStatus.getStatusName() + " sang " + newStatus.getStatusName() + ".";
         addHistoryEntryToJson(order, oldStatus, newStatus, getLoggedInUser(), historyNote);

         // Nếu chuyển sang trạng thái hoàn thành/hủy, có thể cập nhật isPaid
         if (newStatus.getStatusCode().equals("COMPLETED")) { // Giả sử COMPLETED là trạng thái đã thanh toán
             order.setIsPaid(true);
         } else if (newStatus.getStatusCode().equals("CANCELED")) {
             // order.setIsPaid(false); 
         }

         return baseMapper.toDTO(orderRepository.save(order));
     }
}