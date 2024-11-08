package com.sparta.gaeppa.product.dto;

import com.sparta.gaeppa.product.entity.Product;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductResponseDto {
    private UUID productId;
    private String productName;
    private String productDescription;
    private int productPrice;

    public ProductResponseDto(Product product) {
        this.productId = product.getId();
        this.productName = product.getName();
        this.productDescription = product.getDescription();
        this.productPrice = product.getPrice();
    }
}
