package com.sparta.gaeppa.product.dto;

import com.sparta.gaeppa.product.entity.ProductOptionCategory;
import java.util.UUID;
import lombok.Getter;

@Getter
public class ProductOptionCategoryRequestDto {
    private UUID productId;
    private String productOptionCategoryName;
    private int maxLimits;

    public ProductOptionCategory toEntity() {
        return ProductOptionCategory.builder()
                .name(productOptionCategoryName)
                .maxLimits(maxLimits)
                .build();
    }
}
