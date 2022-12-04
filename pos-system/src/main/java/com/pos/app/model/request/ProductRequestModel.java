package com.pos.app.model.request;

import lombok.Data;

import java.time.LocalDate;


@Data
public class ProductRequestModel {
    private String name;
    private double price;
    private String imageUrl;
    private LocalDate date;
    private CategoryRequestModel category;


}
