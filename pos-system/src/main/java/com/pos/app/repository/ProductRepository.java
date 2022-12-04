package com.pos.app.repository;

import com.pos.app.entity.CategoryEntity;
import com.pos.app.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    ProductEntity findByName(String name);
    List<ProductEntity> findByNameContaining(String name);

    List<ProductEntity> findByCategory(CategoryEntity category);

    List<ProductEntity> findByNameContainingOrCategory(String name,CategoryEntity category);

    List<ProductEntity> findByNameContainingAndCategory(String name,CategoryEntity category);
}
