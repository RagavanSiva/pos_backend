package com.pos.app.service;

import com.pos.app.shared.dto.BillDto;

import java.time.LocalDate;
import java.util.List;

public interface BillService {

    BillDto saveBill(BillDto billDto);

    List<BillDto> getBills(String startDate,String endDate);
}
