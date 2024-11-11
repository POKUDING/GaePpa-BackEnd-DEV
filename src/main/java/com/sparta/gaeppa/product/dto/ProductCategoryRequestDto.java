package com.sparta.gaeppa.product.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductCategoryRequestDto {
    @NotBlank
    private String categoryName;
}
