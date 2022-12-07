package com.pos.app.model.request;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class BillRequestModel {

    private LocalDate date;
    private double amount;
    private double salesTax;
    private double discount;
    private List<ProductBillRequestModel> products;

}
