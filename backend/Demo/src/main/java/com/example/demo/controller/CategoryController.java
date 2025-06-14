package com.example.demo.controller;

import com.example.demo.respository.CategoryRepository;
import com.example.dtos.CategoryDTO;
import com.example.services.CategoryService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {
	@Autowired
    CategoryService categoryService;
	@Autowired
	CategoryRepository categoryRepository;

	// Gọi tìm tất cả các Categories
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAll() {
        return ResponseEntity.ok(categoryService.findAll());
    }
	
	// Gọi tìm category theo id
//    @GetMapping("/{id}")
//    public ResponseEntity<CategoryDTO> getById(@PathVariable Long id) {
//        return ResponseEntity.ok(categoryService.findById(id));
//    }

	// Gọi tìm tất cả các Categories dựa vào query parameter
//    @GetMapping
//    public ResponseEntity<List<CategoryDTO>> getByName(
//    		@RequestParam(name = "name", required = false) String name){
//    	return ResponseEntity.ok(categoryService.findByName(name));
//    }
    
    // Thêm 1 Category dựa vào thông tin đóng gói trong body có validate (@Validate)
//    @PostMapping
//    public ResponseEntity<CategoryDTO> create(@RequestBody @Valid CategoryDTO categoryDTO) {
//        return ResponseEntity.ok(categoryService.save(categoryDTO));
//    }
    
    // Thêm 1 Category dựa vào query parameter
    @PostMapping
    public ResponseEntity<CategoryDTO> create(@RequestParam(name="name", required = false) String name) {
    	CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName(name);
        return ResponseEntity.ok(categoryService.save(categoryDTO));
    }
    
    // Thêm 1 Category dựa vào query parameter và có validate
    @PostMapping("/validate")
    public ResponseEntity<?> createValidate(@RequestParam(name="name",required=false) String name){
    	if(name == null || name.isBlank()) {
    		return ResponseEntity.badRequest().body("Name is mandory");
    	}
    	if(name.length()<2||name.length()>50){
    		return ResponseEntity.badRequest().body("Name must be between 2 and 50 characters");
    	}
    	CategoryDTO categoryDTO = new CategoryDTO();
    	categoryDTO.setName(name);
    	return ResponseEntity.ok(categoryService.save(categoryDTO));
    }

    // Cập nhật một row Category dựa trên id được đóng gói trong body có validate (@Valid)
//    @PutMapping("/{id}")
//    public ResponseEntity<CategoryDTO> update(@PathVariable Long id, @RequestBody @Valid CategoryDTO categoryDTO) {
//        return ResponseEntity.ok(categoryService.update(id, categoryDTO));
//    }
    
    // Cập nhật một Category dựa trên id thông qua query parameter
    @PutMapping
    public ResponseEntity<CategoryDTO> update(
    		@RequestParam(name="id") String id,
    		@RequestParam(name="name")String name) {
    	CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName(name);
        return ResponseEntity.ok(categoryService.update(Long.valueOf(id), categoryDTO));
    }

    // Xóa một row Category dựa trên id được đóng gói trong body
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> delete(@PathVariable Long id) {
//        categoryService.delete(id);
//        return ResponseEntity.noContent().build();
//    }
    
    // Xóa một row Category dựa trên id thông qua query parameter
    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam(name="id")String id) {
        categoryService.delete(Long.valueOf(id));
        return ResponseEntity.noContent().build();
    }
    
    // Đọc Header của Request
    @GetMapping("/header")
    public ResponseEntity<String> readHeader(@RequestHeader("X-Client-Id") String clientId){
    	return ResponseEntity.ok("Received clientId from Header: "+clientId);
    }
    
    // Đọc Body của Request
    @PostMapping("/body")
    public ResponseEntity<String> read(@RequestBody CategoryDTO categoryDTO) {
    	return ResponseEntity.ok("Category name from body: " + categoryDTO.getName());
    }
}
