package com.sparta.gaeppa.product.service;

import com.sparta.gaeppa.product.dto.ProductOptionListResponseDto;
import com.sparta.gaeppa.product.entity.ProductOptionCategory;
import com.sparta.gaeppa.product.repository.ProductOptionCategoryRepository;
import java.util.List;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductOptionService {

    private final ProductOptionCategoryRepository productOptionCategoryRepository;

    public ProductOptionListResponseDto getProductOptionsByProductId(UUID productId) {
        List<ProductOptionCategory> productOptionCategoryList = productOptionCategoryRepository.findAllByProductId(
                productId);
        return new ProductOptionListResponseDto(productOptionCategoryList);
    }
}
