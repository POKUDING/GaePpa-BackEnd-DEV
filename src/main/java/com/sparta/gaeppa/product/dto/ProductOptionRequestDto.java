package com.sparta.gaeppa.product.dto;

import com.sparta.gaeppa.product.entity.ProductOption;
import java.util.UUID;
import lombok.Getter;

@Getter
public class ProductOptionRequestDto {
    private UUID productOptionCategoryId;
    private String name;
    private int price;

    public ProductOption toEntity() {
        return ProductOption.builder()
                .name(name)
                .price(price)
                .build();
    }
}
