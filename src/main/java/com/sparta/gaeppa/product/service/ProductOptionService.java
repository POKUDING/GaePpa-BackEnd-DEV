package com.sparta.gaeppa.product.service;

import com.sparta.gaeppa.product.dto.ProductOptionListResponseDto;
import com.sparta.gaeppa.product.dto.ProductOptionRequestDto;
import com.sparta.gaeppa.product.dto.ProductOptionResponseDto;
import com.sparta.gaeppa.product.entity.ProductOption;
import com.sparta.gaeppa.product.entity.ProductOptionCategory;
import com.sparta.gaeppa.product.repository.ProductOptionCategoryRepository;
import com.sparta.gaeppa.product.repository.ProductOptionRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductOptionService {

    private final ProductOptionCategoryRepository productOptionCategoryRepository;
    private final ProductOptionRepository productOptionRepository;

    @Transactional
    public ProductOptionListResponseDto getProductOptionsByProductId(UUID productId) {

        List<ProductOptionCategory> productOptionCategoryList = productOptionCategoryRepository.findAllByProductId(
                productId);

        return new ProductOptionListResponseDto(productOptionCategoryList);
    }

    @Transactional
    public ProductOptionResponseDto createProductOption(ProductOptionRequestDto requestDto) {

        ProductOptionCategory productOptionCategory = productOptionCategoryRepository.findById(
                        requestDto.getProductOptionCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Product Option Category Not Found"));

        ProductOption productOption = requestDto.toEntity();

        productOption.setProductOptionCategory(productOptionCategory);

        return new ProductOptionResponseDto(productOptionRepository.save(productOption));
    }

    @Transactional
    public void updateProductOption(UUID optionId, ProductOptionRequestDto requestDto) {

        ProductOption productOption = productOptionRepository.findById(optionId)
                .orElseThrow(() -> new IllegalArgumentException("Product Option Not Found"));

        ProductOptionCategory productOptionCategory = productOptionCategoryRepository.findById(
                        requestDto.getProductOptionCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Product Option Category Not Found"));

        productOption.setProductOptionCategory(productOptionCategory);

        productOption.update(requestDto);
    }

    @Transactional
    public void deleteProductOption(UUID optionId) {

        ProductOption productOption = productOptionRepository.findById(optionId)
                .orElseThrow(() -> new IllegalArgumentException("Product Option Not Found"));

        productOptionRepository.delete(productOption);
    }
}
