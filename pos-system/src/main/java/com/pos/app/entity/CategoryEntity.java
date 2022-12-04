package com.pos.app.entity;

import lombok.Data;
import lombok.Generated;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "category")
public class CategoryEntity implements Serializable {

    private  static final long serialVersionUID =1L;

    @Id
    @GeneratedValue
    private Long id;
//    @Column(nullable = false)
//    private String categoryId;
    @Column(nullable = false)
    private String categoryName;

}
