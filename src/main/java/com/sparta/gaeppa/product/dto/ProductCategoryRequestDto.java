package com.sparta.gaeppa.product.dto;

import com.sparta.gaeppa.product.entity.ProductCategory;
import jakarta.validation.constraints.NotBlank;
import java.util.UUID;
import lombok.Getter;

@Getter
public class ProductCategoryRequestDto {
    @NotBlank
    private String categoryName;

    @NotBlank
    private UUID productCategoryId;

    public ProductCategory toEntity() {
        return ProductCategory.builder()
                .name(categoryName)
                .storeId(productCategoryId)
                .build();
    }
}
