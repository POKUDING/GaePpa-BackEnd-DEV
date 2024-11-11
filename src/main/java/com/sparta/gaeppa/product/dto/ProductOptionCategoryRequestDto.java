package com.sparta.gaeppa.product.dto;

import java.util.UUID;
import lombok.Getter;

@Getter
public class ProductOptionCategoryRequestDto {
    private UUID productId;
    private String productOptionCategoryName;
    private int maxLimits;
}
