package com.sparta.gaeppa.product.dto;

import com.sparta.gaeppa.product.entity.ProductCategory;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductCategoryResponseDto {
    private UUID id;
    private String name;

    public ProductCategoryResponseDto(ProductCategory productCategory) {
        this.id = productCategory.getId();
        this.name = productCategory.getName();
    }
}
