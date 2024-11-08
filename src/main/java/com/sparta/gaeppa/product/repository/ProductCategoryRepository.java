package com.sparta.gaeppa.product.repository;

import com.sparta.gaeppa.product.entity.ProductCategory;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, UUID> {
}
