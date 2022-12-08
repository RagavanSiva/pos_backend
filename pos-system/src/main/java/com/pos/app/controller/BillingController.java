package com.pos.app.controller;

import com.pos.app.entity.ProductEntity;
import com.pos.app.model.request.BillRequestModel;
import com.pos.app.model.request.ProductBillRequestModel;
import com.pos.app.model.response.BillResponseModel;
import com.pos.app.repository.ProductRepository;
import com.pos.app.service.BillService;
import com.pos.app.service.ProductService;
import com.pos.app.shared.dto.BillDto;
import com.pos.app.shared.dto.ProductBillDto;
import com.pos.app.shared.dto.ProductDto;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("bill")
public class BillingController {

    @Autowired
    private BillService billService;

    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    public BillResponseModel saveBill(@RequestBody BillDto billDto){
//        BillDto billDto  = new ModelMapper().map(billRequestModel,BillDto.class);
        BillDto savedBillDto = billService.saveBill(billDto);
        BillResponseModel billResponseModel = new ModelMapper().map(savedBillDto,BillResponseModel.class);
        return billResponseModel;
    }

    @GetMapping
    public List<BillResponseModel> getAllBillDetails(@RequestParam(value = "startDate",required = false) String startDate ,@RequestParam(value = "endDate",required = false)String endDate){
        List<BillDto> billDtos = billService.getBills(startDate,endDate);
        List<BillResponseModel> billResponseModels = new ArrayList<>();
        for (BillDto billDto : billDtos){
            billResponseModels.add(new ModelMapper().map(billDto,BillResponseModel.class));
        }
        return billResponseModels;
    }
}
