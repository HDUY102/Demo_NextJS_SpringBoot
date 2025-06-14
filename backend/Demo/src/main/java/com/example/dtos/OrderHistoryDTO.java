package com.example.dtos;

import java.util.Date;
import com.example.demo.generics.BaseDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class OrderHistoryDTO extends BaseDTO{
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date changedAt;
    private String changedBy;
    private String note;

    private Long orderId;
    private Long statusId;
    
    private String customerName;
    private String orderDate;
    private String orderStatusCode;
    private String orderStatusName;
}