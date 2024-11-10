package com.sparta.gaeppa.product.dto;

import com.sparta.gaeppa.product.entity.ProductOption;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductOptionResponseDto {
    private UUID productOptionId;
    private String optionName;
    private int optionPrice;

    public ProductOptionResponseDto(ProductOption productOption) {
        this.productOptionId = productOption.getId();
        this.optionName = productOption.getName();
        this.optionPrice = productOption.getPrice();
    }
}
