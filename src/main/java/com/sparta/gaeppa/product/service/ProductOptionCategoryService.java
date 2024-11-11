package com.sparta.gaeppa.product.service;

import com.sparta.gaeppa.global.exception.ExceptionStatus;
import com.sparta.gaeppa.global.exception.ServiceException;
import com.sparta.gaeppa.product.dto.ProductOptionCategoryRequestDto;
import com.sparta.gaeppa.product.dto.ProductOptionCategoryResponseDto;
import com.sparta.gaeppa.product.entity.Product;
import com.sparta.gaeppa.product.entity.ProductOptionCategory;
import com.sparta.gaeppa.product.repository.ProductOptionCategoryRepository;
import com.sparta.gaeppa.product.repository.ProductOptionRepository;
import com.sparta.gaeppa.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductOptionCategoryService {

    private final ProductRepository productRepository;
    private final ProductOptionCategoryRepository productOptionCategoryRepository;
    private final ProductOptionRepository productOptionRepository;

    @Transactional
    public ProductOptionCategoryResponseDto createProductOptionCategory(ProductOptionCategoryRequestDto requestDto) {

        Product product = productRepository.findById(requestDto.getProductId())
                .orElseThrow(() -> new ServiceException(
                        ExceptionStatus.PRODUCT_NOT_FOUND));

        ProductOptionCategory productOptionCategory = requestDto.toEntity();
        productOptionCategory.setProduct(product);

        return ProductOptionCategoryResponseDto.from(productOptionCategoryRepository.save(productOptionCategory));
    }

    @Transactional
    public void updateProductOptionCategory(UUID optionCategoryId, ProductOptionCategoryRequestDto requestDto) {

        ProductOptionCategory productOptionCategory = productOptionCategoryRepository.findById(optionCategoryId)
                .orElseThrow(() -> new ServiceException(
                        ExceptionStatus.PRODUCT_OPTION_CATEGORY_NOT_FOUND));

        productOptionCategory.update(requestDto);
    }

    @Transactional
    public void deleteProductOptionCategory(UUID optionCategoryId) {

        ProductOptionCategory productOptionCategory = productOptionCategoryRepository.findById(optionCategoryId)
                .orElseThrow(() -> new ServiceException(
                        ExceptionStatus.PRODUCT_OPTION_CATEGORY_NOT_FOUND));

        if (!productOptionRepository.existsByProductOptionCategory(productOptionCategory)) {
            throw new ServiceException(ExceptionStatus.PRODUCT_OPTION_CATEGORY_HAS_OPTIONS);
        }

        productOptionCategoryRepository.delete(productOptionCategory);
    }
}
