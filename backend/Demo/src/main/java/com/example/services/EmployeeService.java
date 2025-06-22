package com.example.services;

import java.util.List;
import com.example.demo.entity.Employees;
import com.example.demo.generics.BaseService;
import com.example.dtos.EmployeeDTO;

public interface EmployeeService extends BaseService<Employees, EmployeeDTO, EmployeeDTO>{
	List<EmployeeDTO> findByName(String name);
}