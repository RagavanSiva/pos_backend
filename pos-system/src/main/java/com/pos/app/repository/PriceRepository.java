package com.pos.app.repository;

import com.pos.app.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PriceRepository extends JpaRepository<PriceEntity , Long> {

    List<PriceEntity> findByProductId(String id);

    PriceEntity findByProductIdAndPrice(String id, double price);
}
