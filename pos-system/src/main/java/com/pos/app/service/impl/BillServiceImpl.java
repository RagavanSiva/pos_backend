package com.pos.app.service.impl;

import com.pos.app.entity.BillEntity;
import com.pos.app.entity.ProductBillEntity;
import com.pos.app.entity.ProductEntity;
import com.pos.app.model.request.BillRequestModel;
import com.pos.app.repository.BillRepository;
import com.pos.app.service.BillService;
import com.pos.app.shared.dto.BillDto;
import com.pos.app.shared.dto.ProductBillDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.internal.bytebuddy.description.method.MethodDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BillServiceImpl implements BillService {
    @Autowired
    private BillRepository billRepository;

    @Override
    public BillDto saveBill(BillDto billDto) {
        for (int i = 0; i < billDto.getProducts().size(); i++){
            ProductBillDto productBillDto = billDto.getProducts().get(i);
            productBillDto.setBillDetails(billDto);
            billDto.getProducts().set(i,productBillDto);
        }
        BillEntity billEntity = new ModelMapper().map(billDto,BillEntity.class);
        BillEntity savedBillEntity = billRepository.save(billEntity);
        BillDto savedBillDto = new ModelMapper().map(savedBillEntity,BillDto.class);
        return savedBillDto;
    }

    @Override
    public List<BillDto> getBills(String date) {
        List<BillEntity> billEntities = new ArrayList<>();
        if (date != null) {
            LocalDate localDate = LocalDate.parse(date);
            billEntities = billRepository.findByDate(localDate);
        }else{
           billEntities = billRepository.findAll();
        }

        List<BillDto> billDtos = new ArrayList<>();
        for (BillEntity billEntity: billEntities){
            billDtos.add(new ModelMapper().map(billEntity,BillDto.class));
        }

        return billDtos;
    }
}
