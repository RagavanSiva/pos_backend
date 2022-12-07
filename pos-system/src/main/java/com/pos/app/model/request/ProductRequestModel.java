package com.pos.app.model.request;

import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDate;


@Data
public class ProductRequestModel {
    private String name;
    private double price;
    private String imageUrl;
    private String image;
    private String type;
    private LocalDate date;
    private CategoryRequestModel category;

}
