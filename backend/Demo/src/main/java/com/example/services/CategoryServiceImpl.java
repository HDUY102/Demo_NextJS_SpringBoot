package com.example.services;

import com.example.dtos.CategoryDTO;
import com.example.demo.entity.Category;
import com.example.mapper.CategoryMapper;
import com.example.demo.respository.CategoryRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
	@Autowired
    CategoryRepository categoryRepository;
	@Autowired
    CategoryMapper categoryMapper;

    @Override
    public List<CategoryDTO> findAll() {
        List<Category> categories = categoryRepository.findAll();
        return categoryMapper.toDTOs(categories);
    }

    @Override
    public CategoryDTO findById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        return categoryMapper.toDTO(category);
    }
    
    @Override
    public List<CategoryDTO> findByName(String name) {
        return categoryMapper.toDTOs(categoryRepository.findByName(name));
    }

    @Override
    public CategoryDTO save(CategoryDTO categoryDTO) {
        Category category = categoryMapper.toEntity(categoryDTO);
        return categoryMapper.toDTO(categoryRepository.save(category));
    }

    @Override
    public CategoryDTO update(Long id, CategoryDTO categoryDTO) {
        Category existing = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        
        existing.setName(categoryDTO.getName()); // cập nhật trường Name, nếu không có thì sẽ cập nhật full field trong db
        
        return categoryMapper.toDTO(categoryRepository.save(existing));
    }

    @Override
    public void delete(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("Category not found with id: " + id);
        }
        categoryRepository.deleteById(id);
    }
}
