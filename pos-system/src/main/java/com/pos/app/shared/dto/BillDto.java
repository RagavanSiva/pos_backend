package com.pos.app.shared.dto;

import com.pos.app.entity.ProductBillEntity;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class BillDto {
    private Long id;
    private LocalDate date;
    private double amount;
    private double salesTax;
    private double discount;
    private List<ProductBillDto> products;
}
