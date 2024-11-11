package com.sparta.gaeppa.product.dto;

import com.sparta.gaeppa.product.entity.Product;
import com.sparta.gaeppa.product.entity.ProductCategory;
import com.sparta.gaeppa.product.entity.ProductOption;
import com.sparta.gaeppa.product.entity.ProductOptionCategory;

public class ProductMapper {
    public static ProductCategory productCategoryRequestDtotoProductCategory(
            ProductCategoryRequestDto productCategoryRequestDto) {
        return ProductCategory.builder()
                .name(productCategoryRequestDto.getCategoryName())
                .build();
    }

    public static Product ProductRequestDtoToProduct(ProductRequestDto productRequestDto) {
        return Product.builder()
                .name(productRequestDto.getProductName())
                .price(productRequestDto.getProductPrice())
                .description(productRequestDto.getProductDescription())
                .build();
    }

    public static ProductOptionCategory productOptionCategoryRequestDtoToProductOptionCategory(
            ProductOptionCategoryRequestDto productOptionCategoryRequestDto) {
        return ProductOptionCategory.builder()
                .name(productOptionCategoryRequestDto.getProductOptionCategoryName())
                .maxLimits(productOptionCategoryRequestDto.getMaxLimits())
                .build();
    }

    public static ProductOption ProductOptionRequestDtoToProductOption(
            ProductOptionRequestDto productOptionRequestDto) {
        return ProductOption.builder()
                .name(productOptionRequestDto.getName())
                .price(productOptionRequestDto.getPrice())
                .build();
    }
}
