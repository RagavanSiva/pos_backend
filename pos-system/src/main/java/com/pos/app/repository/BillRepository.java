package com.pos.app.repository;

import com.pos.app.entity.BillEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BillRepository extends JpaRepository<BillEntity, Long> {

    List<BillEntity> findByDate(LocalDate date);
    List<BillEntity> findByDateBetween(LocalDate startDate,LocalDate endDate);
}
