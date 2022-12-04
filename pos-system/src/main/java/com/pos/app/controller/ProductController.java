package com.pos.app.controller;

import com.pos.app.model.request.ProductRequestModel;
import com.pos.app.model.response.ProductResponseModel;
import com.pos.app.service.ProductService;
import com.pos.app.shared.dto.ProductDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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


}
