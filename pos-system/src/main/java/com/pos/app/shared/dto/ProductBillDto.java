package com.pos.app.shared.dto;

import lombok.Data;


@Data
public class ProductBillDto {
    private  Long productBillId;
    private String productId;
    private int quantity;
    private BillDto billDetails;

}
