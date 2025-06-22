package com.example.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Employees;
import com.example.demo.generics.BaseServiceImpl;
import com.example.demo.respository.EmployeeRepository;
import com.example.dtos.*;
import com.example.mapper.EmployeeMapper;

@Service
public class EmployeeServiceImpl extends BaseServiceImpl<Employees, EmployeeDTO, EmployeeDTO> implements EmployeeService{
	private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        super(employeeRepository, employeeMapper);
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<EmployeeDTO> findByName(String nameEmployee) {
    	return baseMapper.toDtoList(employeeRepository.findByNameEmployee(nameEmployee));
    }
}