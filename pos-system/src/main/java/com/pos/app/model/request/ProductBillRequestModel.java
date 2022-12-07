package com.pos.app.model.request;

import lombok.Data;

@Data
public class ProductBillRequestModel {

    private String productId;
    private int quantity;

}
