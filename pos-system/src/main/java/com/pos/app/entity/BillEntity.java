package com.pos.app.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "bill")
public class BillEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private LocalDate date;
    @Column(nullable = false)
    private double amount;
    private double salesTax;
    private double discount;

    @OneToMany(mappedBy = "billDetails", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProductBillEntity> products;


}
