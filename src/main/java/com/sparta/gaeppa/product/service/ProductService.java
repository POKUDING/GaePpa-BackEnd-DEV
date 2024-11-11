package com.sparta.gaeppa.product.service;

import com.sparta.gaeppa.global.exception.ExceptionStatus;
import com.sparta.gaeppa.global.exception.ServiceException;
import com.sparta.gaeppa.product.dto.product.ProductRequestDto;
import com.sparta.gaeppa.product.dto.product.ProductResponseDto;
import com.sparta.gaeppa.product.dto.product.StoreProductListResponseDto;
import com.sparta.gaeppa.product.entity.Product;
import com.sparta.gaeppa.product.entity.ProductCategory;
import com.sparta.gaeppa.product.repository.ProductCategoryRepository;
import com.sparta.gaeppa.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductRepository productRepository;

    @Transactional
    public StoreProductListResponseDto getAllProductsByStoreId(UUID storeId) {

        storeId = null;
        List<ProductCategory> productCategoryList = productCategoryRepository.findAllByStore_StoreId(storeId);

        if (productCategoryList.isEmpty()) {
            throw new ServiceException(ExceptionStatus.PRODUCT_CATEGORY_NOT_FOUND);
        }

        return new StoreProductListResponseDto(productCategoryList);
    }

    @Transactional
    public ProductResponseDto createProduct(ProductRequestDto requestDto) {

        ProductCategory productCategory = productCategoryRepository.findById(requestDto.getProductCategoryId())
                .orElseThrow(() -> new ServiceException(ExceptionStatus.PRODUCT_CATEGORY_NOT_FOUND));

        Product newProduct = requestDto.toEntity();
        newProduct.setProductCategory(productCategory);

        return ProductResponseDto.from(productRepository.save(newProduct));
    }

    @Transactional
    public void updateProduct(UUID productId, ProductRequestDto requestDto) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ServiceException(ExceptionStatus.PRODUCT_NOT_FOUND));

        ProductCategory productCategory = productCategoryRepository.findById(requestDto.getProductCategoryId())
                .orElseThrow(() -> new ServiceException(ExceptionStatus.PRODUCT_CATEGORY_NOT_FOUND));

        product.setProductCategory(productCategory);
        product.update(requestDto.getProductName(), requestDto.getProductDescription(), requestDto.getProductPrice(),
                requestDto.isHideStatus());
    }

    @Transactional
    public void deleteProduct(UUID productId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ServiceException(ExceptionStatus.PRODUCT_NOT_FOUND));

        productRepository.delete(product);
    }
}
