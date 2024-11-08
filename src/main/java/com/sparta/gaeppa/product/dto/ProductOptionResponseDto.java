package com.sparta.gaeppa.product.dto;

import com.sparta.gaeppa.product.entity.ProductOption;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductOptionResponseDto {
    private String optionName;
    private int optionPrice;

    public ProductOptionResponseDto(ProductOption productOption) {
        this.optionName = productOption.getName();
        this.optionPrice = productOption.getPrice();
    }
}
