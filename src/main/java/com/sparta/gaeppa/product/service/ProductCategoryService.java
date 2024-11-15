package com.sparta.gaeppa.product.service;

import com.sparta.gaeppa.global.exception.ExceptionStatus;
import com.sparta.gaeppa.global.exception.ServiceException;
import com.sparta.gaeppa.product.dto.productCategory.ProductCategoryRequestDto;
import com.sparta.gaeppa.product.dto.productCategory.ProductCategoryResponseDto;
import com.sparta.gaeppa.product.entity.ProductCategory;
import com.sparta.gaeppa.product.repository.ProductCategoryRepository;
import com.sparta.gaeppa.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;
    private final ProductRepository productRepository;

    @Transactional
    public ProductCategoryResponseDto createProductCategory(ProductCategoryRequestDto requestDto) {

//        Store store = new Store()

        ProductCategory newProductCategory = requestDto.toEntity();
        //TODO: 인증 객체를 통해 storeId를 가져와서 저장

        ProductCategory savedProductCategory = productCategoryRepository.save(requestDto.toEntity());

        return ProductCategoryResponseDto.from(savedProductCategory);
    }

    @Transactional
    public void updateProductCategory(UUID productCategoryId, ProductCategoryRequestDto requestDto) {

        ProductCategory productCategory = productCategoryRepository.findById(productCategoryId)
                .orElseThrow(() -> new ServiceException(ExceptionStatus.PRODUCT_CATEGORY_NOT_FOUND));

        productCategory.updateName(requestDto.getCategoryName());
    }

    @Transactional
    public void deleteProductCategory(UUID productCategoryId) {
        ProductCategory productCategory = productCategoryRepository.findById(productCategoryId)
                .orElseThrow(() -> new ServiceException(ExceptionStatus.PRODUCT_CATEGORY_NOT_FOUND));

        if (productRepository.existsByProductCategory(productCategory)) {
            throw new ServiceException(ExceptionStatus.PRODUCT_CATEGORY_HAS_PRODUCTS);
        }

        productCategoryRepository.deleteById(productCategoryId);
    }
}
