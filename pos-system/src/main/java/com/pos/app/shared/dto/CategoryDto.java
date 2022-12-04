package com.pos.app.shared.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryDto implements Serializable {

    private  static final long serialVersionUID =1L;
    private Long id;
//    private String categoryId;
    private String categoryName;
}
