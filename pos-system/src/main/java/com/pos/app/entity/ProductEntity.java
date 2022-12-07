package com.pos.app.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
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
    @Lob
    @Column(length=100000)
    private String imageUrl;
    @Column(name = "image", unique = false,  length = 100000)
    private String image;
    private String path;
    @Column(nullable = false)
    private double price;
    @Column(nullable = false)
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;
}
