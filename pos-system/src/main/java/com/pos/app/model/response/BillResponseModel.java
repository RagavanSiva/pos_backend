package com.pos.app.model.response;

import com.pos.app.model.request.ProductBillRequestModel;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;


@Data
public class BillResponseModel {

    private Long billId;
    private LocalDate date;
    private double amount;
    private double salesTax;
    private double discount;
    private List<ProductBillResponseModel> products;
}
