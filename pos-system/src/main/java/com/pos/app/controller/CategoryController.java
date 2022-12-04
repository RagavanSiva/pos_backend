package com.pos.app.controller;

import com.pos.app.model.request.CategoryRequestModel;
import com.pos.app.model.response.CategoryResponseModel;
import com.pos.app.service.CategoryService;
import com.pos.app.shared.dto.CategoryDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public CategoryResponseModel saveCategory(@RequestBody  CategoryRequestModel categoryRequestModel){
        ModelMapper modelMapper = new ModelMapper();
        CategoryDto categoryDto = modelMapper.map(categoryRequestModel,CategoryDto.class);
        CategoryDto savedCategory = categoryService.saveCategory(categoryDto);
        CategoryResponseModel returnValue = new ModelMapper().map(savedCategory, CategoryResponseModel.class);
        return  returnValue;

    }

    @GetMapping("/{id}")
    public CategoryResponseModel getCategoryById(@Nullable  @PathVariable("id") long id){
        CategoryDto categoryDto = categoryService.getCategoryById(id);
        CategoryResponseModel returnValue = new ModelMapper().map(categoryDto,CategoryResponseModel.class);
        return  returnValue;
    }
    @GetMapping()
    public List<CategoryResponseModel> getAllCategories(){
        List<CategoryDto> categories = categoryService.getAllCategory();
       List<CategoryResponseModel> categoryResponseModels = new ArrayList<>();
       for (CategoryDto categoryDto : categories){
           categoryResponseModels.add(new ModelMapper().map(categoryDto,CategoryResponseModel.class));
       }
        return  categoryResponseModels;
    }



}
