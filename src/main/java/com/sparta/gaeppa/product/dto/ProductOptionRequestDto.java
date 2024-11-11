package com.sparta.gaeppa.product.dto;

import java.util.UUID;
import lombok.Getter;

@Getter
public class ProductOptionRequestDto {
    private UUID productOptionCategoryId;
    private String name;
    private int price;
}
