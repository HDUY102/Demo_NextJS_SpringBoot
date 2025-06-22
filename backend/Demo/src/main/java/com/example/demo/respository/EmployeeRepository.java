package com.example.demo.respository;

import java.util.List;
import com.example.demo.entity.Employees;
import com.example.demo.generics.BaseRepository;

public interface EmployeeRepository extends BaseRepository<Employees>{
	List<Employees> findByNameEmployee(String Employee);
}
