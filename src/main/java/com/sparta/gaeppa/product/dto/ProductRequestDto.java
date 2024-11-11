package com.sparta.gaeppa.product.dto;

import java.util.UUID;
import lombok.Getter;

@Getter
public class ProductRequestDto {
    private UUID productCategoryId;
    private String productName;
    private int productPrice;
    private String productDescription;
    private boolean hideStatus;
}
