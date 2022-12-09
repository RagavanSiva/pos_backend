package com.pos.app.shared.dto;

import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDate;

@Data
public class PriceDto {
    private Long id;

    private String productId;

    private double price;

    private LocalDate date;
}
