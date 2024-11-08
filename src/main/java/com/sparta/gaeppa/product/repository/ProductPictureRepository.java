package com.sparta.gaeppa.product.repository;

import com.sparta.gaeppa.product.entity.ProductPicture;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPictureRepository extends JpaRepository<ProductPicture, UUID> {
}
