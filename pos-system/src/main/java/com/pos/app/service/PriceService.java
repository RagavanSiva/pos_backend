package com.pos.app.service;

import com.pos.app.shared.dto.PriceDto;

import java.util.List;

public interface PriceService {
    PriceDto savePrice(PriceDto priceDto);

    List<PriceDto> getAllPrices(String id);

    PriceDto getPriceByProductIdAndPrice(String id, double price);

}
