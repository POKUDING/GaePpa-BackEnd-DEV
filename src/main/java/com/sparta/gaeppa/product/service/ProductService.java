package com.sparta.gaeppa.product.service;

import com.sparta.gaeppa.product.dto.StoreProductsResponseDto;
import com.sparta.gaeppa.product.entity.ProductCategory;
import com.sparta.gaeppa.product.repository.ProductCategoryRepository;
import com.sparta.gaeppa.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductService {
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductRepository productRepository;

    @Transactional
    public StoreProductsResponseDto getAllProductsByStoreId(UUID storeId) {
        List<ProductCategory> productCategoryList = productCategoryRepository.findAllByStoreId(storeId);
        return StoreProductsResponseDto.builder()
                .categories(productCategoryList)
                .build();
    }
}
