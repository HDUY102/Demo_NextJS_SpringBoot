package com.example.dtos;
import com.example.demo.generics.BaseDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class EmployeeDTO extends BaseDTO{
	private String phoneEmployee;
	private String addressEmployee;
	private String role;
}