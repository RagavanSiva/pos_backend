package com.pos.app.shared.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class ProductDto implements Serializable {
    private  static final long serialVersionUID =1L;

    private Long id;
    private String productId;
    private String name;
    private double price;
    private String imageUrl;
    private LocalDate date;
    private CategoryDto category;


}
