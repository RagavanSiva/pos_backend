package com.pos.app.service;

import com.pos.app.shared.dto.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto saveProduct(ProductDto productDto);

    List<ProductDto> getAllProducts(Long id,String name);

    ProductDto updateProduct(String id, ProductDto productDto);
}
