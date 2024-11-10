package com.sparta.gaeppa.product.dto;

import com.sparta.gaeppa.product.entity.Product;
import java.util.UUID;
import lombok.Getter;

@Getter
public class ProductRequestDto {
    private UUID productCategoryId;
    private String productName;
    private int productPrice;
    private String productDescription;

    public Product toEntity() {
        return Product.builder()
                .name(productName)
                .price(productPrice)
                .description(productDescription)
                .build();
    }
}
