package com.sparta.gaeppa.product.service;

import com.sparta.gaeppa.global.exception.ExceptionStatus;
import com.sparta.gaeppa.global.exception.RepositoryException;
import com.sparta.gaeppa.product.dto.ProductCategoryRequestDto;
import com.sparta.gaeppa.product.entity.ProductCategory;
import com.sparta.gaeppa.product.repository.ProductCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;

    public void createProductCategory(ProductCategoryRequestDto requestDto) {
        productCategoryRepository.save(requestDto.toEntity());
    }

    public void updateProductCategory(ProductCategoryRequestDto requestDto) {
        ProductCategory productCategory = productCategoryRepository.findById(requestDto.ge)
                .orElseThrow(() -> new RepositoryException(ExceptionStatus.PRODUCT_CATEGORY_NOT_FOUND));
        productCategory.update(requestDto.getCategoryName());
    }
}
