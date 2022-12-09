package com.pos.app.service.impl;

import com.pos.app.entity.PriceEntity;
import com.pos.app.repository.PriceRepository;
import com.pos.app.service.PriceService;
import com.pos.app.shared.dto.PriceDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PriceServiceImpl implements PriceService {

    @Autowired
    private PriceRepository priceRepository;

    @Override
    public PriceDto savePrice(PriceDto priceDto) {
        PriceEntity price = new ModelMapper().map(priceDto,PriceEntity.class);
        PriceEntity savedPrice = priceRepository.save(price);
        PriceDto returnValue = new ModelMapper().map(savedPrice,PriceDto.class);
        return  returnValue;
    }

    @Override
    public List<PriceDto> getAllPrices(String id) {
       List<PriceEntity> priceEntities = priceRepository.findByProductId(id);
       List<PriceDto> priceDtos = new ArrayList<>();
       for (PriceEntity price : priceEntities){
           priceDtos.add(new ModelMapper().map(price,PriceDto.class));
       }
       return priceDtos;
    }

    @Override
    public PriceDto getPriceByProductIdAndPrice(String id, double price) {
        PriceEntity priceEntity = priceRepository.findByProductIdAndPrice(id,price);
        PriceDto priceDto = new ModelMapper().map(priceEntity, PriceDto.class);
        return priceDto;
    }
}
