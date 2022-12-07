package com.pos.app.shared.dto;

import lombok.Data;

import javax.persistence.Column;
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
    private String image;
    private String type;
    private LocalDate date;
    private CategoryDto category;


}
