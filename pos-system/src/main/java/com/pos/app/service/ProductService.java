package com.pos.app.service;

import com.pos.app.shared.dto.ProductDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    ProductDto saveProduct(ProductDto productDto);

    ProductDto saveProductWithImage(MultipartFile file , ProductDto productDto);

    List<ProductDto> getAllProducts(Long id,String name);

    ProductDto updateProduct(String id, ProductDto productDto);


}
