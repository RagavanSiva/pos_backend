package com.pos.app.controller;

import com.pos.app.service.PriceService;
import com.pos.app.shared.dto.PriceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("price")
public class PriceController {
    @Autowired
    private PriceService priceService;

    @GetMapping("/{id}")
    public List<PriceDto> getAllPriceByProductId(@PathVariable("id") String id){
        List<PriceDto> priceDtos = priceService.getAllPrices(id);
        return priceDtos;
    }
}
