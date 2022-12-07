package com.pos.app.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "products")
public class ProductEntity implements Serializable {
    private  static final long serialVersionUID =1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String productId;
    @Column(nullable = false)
    private String name;
    private String imageUrl;
    @Column(nullable = false)
    private double price;
    @Column(nullable = false)
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;
}
