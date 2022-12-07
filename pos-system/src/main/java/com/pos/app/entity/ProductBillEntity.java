package com.pos.app.entity;

import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "product_bill")
public class ProductBillEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long productBillId;
//    @Column(nullable = false)
//    private String productBillId;
//    @Column(nullable = false)
    private String productId;
    @Column(nullable = false)
    private int quantity;

    @JoinColumn( name = "billId")
    @ManyToOne(fetch = FetchType.LAZY)
    private BillEntity billDetails;


}
