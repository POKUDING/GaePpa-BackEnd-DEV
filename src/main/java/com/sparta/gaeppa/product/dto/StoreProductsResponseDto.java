package com.sparta.gaeppa.product.dto;

import com.sparta.gaeppa.product.entity.ProductCategory;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Getter
public class StoreProductsResponseDto {
    private int totalAmount;
    private List<CategoryResponseDto> details;

    @Builder
    protected StoreProductsResponseDto(List<ProductCategory> categories) {
        this.totalAmount = categories.size();
        this.details = categories.stream().map(CategoryResponseDto::new).toList();
    }

    @Getter
    private class CategoryResponseDto {
        private String category;
        private List<ProductResponseDto> products;


        public CategoryResponseDto(ProductCategory category) {
            this.category = category.getName();
            this.products = category.getProducts().stream().map(ProductResponseDto::new).toList();
        }
    }
}
