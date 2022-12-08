package com.pos.app.service;

import com.pos.app.shared.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto saveCategory(CategoryDto categoryDto);

    CategoryDto getCategoryById(Long id);

    List<CategoryDto> getAllCategory();

    CategoryDto updateCategory(Long id,CategoryDto categoryDto);

}
