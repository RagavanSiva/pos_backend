package com.pos.app.model.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProductResponseModel {

    private String productId;
    private String name;
    private double price;
    private String imageUrl;
    private LocalDate date;
    private CategoryResponseModel category;
}
