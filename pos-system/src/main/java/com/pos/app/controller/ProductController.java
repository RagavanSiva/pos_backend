package com.pos.app.controller;

import com.pos.app.model.request.ProductRequestModel;
import com.pos.app.model.response.ProductResponseModel;
import com.pos.app.service.ProductService;
import com.pos.app.shared.dto.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public ProductResponseModel saveProduct(@RequestBody ProductRequestModel productRequestModel){
        ModelMapper modelMapper = new ModelMapper();
        ProductDto productDto = modelMapper.map(productRequestModel,ProductDto.class);
        ProductDto savedProduct = productService.saveProduct(productDto);
        ProductResponseModel responseModel = new ModelMapper().map(savedProduct,ProductResponseModel.class);
        return responseModel;
    }

    @GetMapping()
    public List<ProductResponseModel> getAllProducts(  @RequestParam(value = "id",required = false) Long id ,@RequestParam(value = "name",required = false ,defaultValue = "") String name){
        List<ProductDto> productDtos = productService.getAllProducts(id,name);
        List<ProductResponseModel> productResponseModelList = new ArrayList<>();
        for (ProductDto productDto : productDtos){
            productResponseModelList.add(new ModelMapper().map(productDto,ProductResponseModel.class));
        }
        return productResponseModelList;

    }

    @PutMapping("/{id}")
    public ProductResponseModel updateProduct(@PathVariable("id") String id ,@RequestBody ProductRequestModel productRequestModel){
        ProductDto productDto = new ModelMapper().map(productRequestModel,ProductDto.class);
        ProductDto updatedProduct = productService.updateProduct(id,productDto);
        ProductResponseModel responseModel = new ModelMapper().map(updatedProduct,ProductResponseModel.class);
        return responseModel;
    }


}
