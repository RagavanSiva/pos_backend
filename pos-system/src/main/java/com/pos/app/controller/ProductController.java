package com.pos.app.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pos.app.entity.ProductEntity;
import com.pos.app.model.request.ProductRequestModel;
import com.pos.app.model.response.ProductResponseModel;
import com.pos.app.repository.ProductRepository;
import com.pos.app.service.FileStorageService;
import com.pos.app.service.ProductService;
import com.pos.app.shared.dto.ProductDto;
import com.pos.app.shared.utils.ImageUtility;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    FileStorageService storageService;

    private String FILE_PATH_ROOT = "./uploads/";

    @PostMapping("/save")
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

    @PutMapping("save/{id}")
    public ProductResponseModel updateProduct(@PathVariable("id") String id ,@RequestBody ProductRequestModel productRequestModel){
        ProductDto productDto = new ModelMapper().map(productRequestModel,ProductDto.class);
        ProductDto updatedProduct = productService.updateProduct(id,productDto);
        ProductResponseModel responseModel = new ModelMapper().map(updatedProduct,ProductResponseModel.class);
        return responseModel;
    }


    @PostMapping
    public ProductResponseModel uploadFile(@RequestParam("image") MultipartFile file,@RequestParam("product") String productRequestModel) {
        ProductRequestModel response;
        try {
            response = objectMapper.readValue(productRequestModel, ProductRequestModel.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        if (file.isEmpty()){
            throw new RuntimeException("Image needed");
        }
        ModelMapper modelMapper = new ModelMapper();
        ProductDto productDto = modelMapper.map(response, ProductDto.class);
        ProductDto savedProduct = productService.saveProductWithImage(file,productDto);
        ProductResponseModel responseModel = new ModelMapper().map(savedProduct, ProductResponseModel.class);
        return responseModel;

    }

    @PutMapping("/{id}")
    public ProductResponseModel updateProductWithFile(@PathVariable("id")String id, @Nullable @RequestParam(value = "image") MultipartFile file, @RequestParam("product") String productRequestModel) {
        ProductRequestModel response;
        try {
            response = objectMapper.readValue(productRequestModel, ProductRequestModel.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        ModelMapper modelMapper = new ModelMapper();
        ProductDto productDto = modelMapper.map(response, ProductDto.class);
        ProductDto savedProduct = productService.updateProductWithImage(id,file,productDto);
        ProductResponseModel responseModel = new ModelMapper().map(savedProduct, ProductResponseModel.class);
        return responseModel;

    }

    @GetMapping("/{filename}")
    public ResponseEntity<byte[]> getImage(@PathVariable("filename") String filename) {
        byte[] image = new byte[0];
        try {
            image = FileUtils.readFileToByteArray(new File(FILE_PATH_ROOT+filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
    }


}
