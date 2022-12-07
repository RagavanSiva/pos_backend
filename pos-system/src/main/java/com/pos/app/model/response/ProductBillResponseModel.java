package com.pos.app.model.response;

import lombok.Data;

@Data
public class ProductBillResponseModel {

    private Long productBillId;
    private String productId;
    private int quantity;

}
