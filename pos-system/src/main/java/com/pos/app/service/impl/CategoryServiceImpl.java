package com.pos.app.service.impl;

import com.pos.app.entity.CategoryEntity;
import com.pos.app.repository.CategoryRepository;
import com.pos.app.service.CategoryService;
import com.pos.app.shared.dto.CategoryDto;
import com.pos.app.shared.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private Utils utils;


    @Override
    public CategoryDto saveCategory(CategoryDto categoryDto) {
        ModelMapper modelMapper = new ModelMapper();
        CategoryEntity category = categoryRepository.findByCategoryName(categoryDto.getCategoryName());
        if (category != null) throw  new RuntimeException("Category already exists");

        CategoryEntity categoryEntity = modelMapper.map(categoryDto,CategoryEntity.class);

//        String publicCategoryId = utils.generateCategoryId(4);
//        categoryEntity.setCategoryId(publicCategoryId);
        CategoryEntity savedCategory = categoryRepository.save(categoryEntity);
        CategoryDto returnValue = new ModelMapper().map(savedCategory,CategoryDto.class);
        return  returnValue;

    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        CategoryEntity category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not Exists"));
        CategoryDto categoryDto = new ModelMapper().map(category,CategoryDto.class);
        return categoryDto;
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<CategoryEntity> categoryEntities = categoryRepository.findAll();
        List<CategoryDto> categoryDtos = new ArrayList<>();
        for (CategoryEntity categoryEntity : categoryEntities){
            categoryDtos.add(new ModelMapper().map(categoryEntity,CategoryDto.class));
        }
        return categoryDtos;
    }

    @Override
    public CategoryDto updateCategory(Long id,CategoryDto categoryDto) {
        CategoryEntity category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not Exists"));
        CategoryEntity checkCategoryName = categoryRepository.findByCategoryName(categoryDto.getCategoryName());
        if (checkCategoryName != null){
            throw new RuntimeException("Category Name Already Exists");
        }
        category.setCategoryName(categoryDto.getCategoryName());
        CategoryEntity categoryEntity = categoryRepository.save(category);
        CategoryDto updatedCategory = new  ModelMapper().map(categoryEntity, CategoryDto.class);
        return updatedCategory;
    }

}
