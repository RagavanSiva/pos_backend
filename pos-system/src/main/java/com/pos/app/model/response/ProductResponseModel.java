package com.pos.app.model.response;

import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDate;

@Data
public class ProductResponseModel {

    private String productId;
    private String name;
    private double price;
    private String imageUrl;
    private String image;
    private String type;
    private LocalDate date;
    private CategoryResponseModel category;
}
