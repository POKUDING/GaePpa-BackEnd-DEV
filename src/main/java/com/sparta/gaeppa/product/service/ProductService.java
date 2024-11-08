package com.sparta.gaeppa.product.service;

import com.sparta.gaeppa.product.dto.StoreProductListResponseDto;
import com.sparta.gaeppa.product.entity.ProductCategory;
import com.sparta.gaeppa.product.repository.ProductCategoryRepository;
import com.sparta.gaeppa.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductRepository productRepository;

    @Transactional
    public StoreProductListResponseDto getAllProductsByStoreId(UUID storeId) {
        List<ProductCategory> productCategoryList = productCategoryRepository.findAllByStoreId(storeId);
        return new StoreProductListResponseDto(productCategoryList);
    }
}
