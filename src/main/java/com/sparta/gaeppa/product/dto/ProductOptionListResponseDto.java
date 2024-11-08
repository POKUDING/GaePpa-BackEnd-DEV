package com.sparta.gaeppa.product.dto;

import com.sparta.gaeppa.product.entity.ProductOptionCategory;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductOptionListResponseDto {
    private int optionCategoryAmount;
    private List<ProductOptionCategoryResponseDto> options;

    public ProductOptionListResponseDto(List<ProductOptionCategory> productOptionCategories) {
        this.optionCategoryAmount = productOptionCategories.size();
        this.options = productOptionCategories.stream().map(ProductOptionCategoryResponseDto::new).toList();
    }

    @Getter
    private class ProductOptionCategoryResponseDto {
        private String optionCategory;
        private List<ProductOptionResponseDto> options;

        public ProductOptionCategoryResponseDto(ProductOptionCategory productOptionCategory) {
            this.optionCategory = productOptionCategory.getName();
            this.options = productOptionCategory.getOptions().stream().map(ProductOptionResponseDto::new).toList();
        }
    }
}
