package com.sparta.gaeppa.product.dto;

import com.sparta.gaeppa.product.entity.ProductOptionCategory;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductOptionCategoryResponseDto {
    private UUID productOptionCategoryId;
    private String productOptionCategoryName;
    private int maxLimits;

    public ProductOptionCategoryResponseDto(ProductOptionCategory productOptionCategory) {
        this.productOptionCategoryId = productOptionCategory.getId();
        this.productOptionCategoryName = productOptionCategory.getName();
        this.maxLimits = productOptionCategory.getMaxLimits();
    }
}
