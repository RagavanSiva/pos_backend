package com.pos.app.service.impl;

import com.pos.app.entity.CategoryEntity;
import com.pos.app.entity.ProductEntity;
import com.pos.app.repository.CategoryRepository;
import com.pos.app.repository.ProductRepository;
import com.pos.app.service.CategoryService;
import com.pos.app.service.ProductService;
import com.pos.app.shared.dto.CategoryDto;
import com.pos.app.shared.dto.ProductDto;
import com.pos.app.shared.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private Utils utils;

    @Override
    public ProductDto saveProduct(ProductDto productDto) {
        ModelMapper modelMapper = new ModelMapper();
        ProductEntity checkProduct = productRepository.findByName(productDto.getName());
        if (checkProduct != null) throw  new RuntimeException("Record Already Exists");

        ProductEntity productEntity = modelMapper.map(productDto, ProductEntity.class);
        String publicProductId = utils.generateProductId(10);
        productEntity.setProductId(publicProductId);
        CategoryDto existingCategory = categoryService.getCategoryById(productDto.getCategory().getId());
        CategoryEntity category = new ModelMapper().map(existingCategory,CategoryEntity.class);
        productEntity.setCategory(category);
        ProductEntity savedProduct = productRepository.save(productEntity);
        ProductDto returnValue = new ModelMapper().map(savedProduct,ProductDto.class);

        return returnValue;

    }

    @Override
    public List<ProductDto> getAllProducts(Long id,String name) {
        List<ProductEntity> products = new ArrayList<>();
//        if(id != null) {
//            CategoryEntity category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not exits"));
//            products  = productRepository.findByCategory(category);
//        }else{
//            if (!name.isEmpty() && name != null){
//                products.add(productRepository.findByName(name));
//            }else{
//                products = productRepository.findAll();
//            }
//
//        }

        if(!name.isEmpty()  && id != null){
            CategoryEntity category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not exits"));
            products = productRepository.findByNameContainingAndCategory(name,category);
        }
        else if(id == null  ){
            products =  productRepository.findByNameContaining(name);
        }
        else if( name.isEmpty()  ){
            CategoryEntity category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not exits"));
            products =  productRepository.findByCategory(category);
        }
        else {
            products = productRepository.findAll();
        }

        if(products.isEmpty()) throw new RuntimeException("No Data");
        List<ProductDto> productDtoList = new ArrayList<>();
        for (ProductEntity productEntity : products){
            productDtoList.add(new ModelMapper().map(productEntity,ProductDto.class));
        }
        return productDtoList;
    }

    @Override
    public ProductDto updateProduct(String id, ProductDto productDto) {
            ProductEntity productEntity = productRepository.findByProductId(id);
            if (productEntity == null) throw new RuntimeException("No Record Found");
            productEntity.setName(productDto.getName());
            productEntity.setDate(productDto.getDate());
            productEntity.setImageUrl(productDto.getImageUrl());
            CategoryEntity category = new ModelMapper().map(productDto.getCategory(),CategoryEntity.class);
            productEntity.setCategory(category);

            ProductEntity updatedProduct = productRepository.save(productEntity);
            ProductDto updatedProductDto = new ModelMapper().map(updatedProduct,ProductDto.class);

        return updatedProductDto;
    }
}
