package com.example.demo.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.Employees;
import com.example.demo.generics.*;
import com.example.dtos.EmployeeDTO;
import com.example.services.EmployeeService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController extends BaseController<EmployeeDTO, EmployeeDTO>{
	private final EmployeeService employeeService;

    @Override
    protected BaseService<Employees, EmployeeDTO, EmployeeDTO> getService() {
        return employeeService;
    }

    @GetMapping("/search")
    public ResponseEntity<List<EmployeeDTO>> searchByName(@RequestParam(name="name", required = false) String nameEmployee) {
        return ResponseEntity.ok(employeeService.findByName(nameEmployee));
    }
}
