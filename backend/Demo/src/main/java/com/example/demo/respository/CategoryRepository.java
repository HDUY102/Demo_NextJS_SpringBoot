package com.example.demo.respository;

import com.example.demo.entity.Category;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	List<Category> findByName(String name);
}
