package com.sparta.gaeppa.product.service;

import com.sparta.gaeppa.global.exception.ExceptionStatus;
import com.sparta.gaeppa.global.exception.RepositoryException;
import com.sparta.gaeppa.product.dto.ProductRequestDto;
import com.sparta.gaeppa.product.dto.ProductResponseDto;
import com.sparta.gaeppa.product.dto.StoreProductListResponseDto;
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

        List<ProductCategory> productCategoryList = productCategoryRepository.findAllByStoreId(storeId);

        return new StoreProductListResponseDto(productCategoryList);
    }

    public ProductResponseDto createProduct(ProductRequestDto requestDto) {

        ProductCategory productCategory = productCategoryRepository.findById(requestDto.getProductCategoryId())
                .orElseThrow(() -> new RepositoryException(ExceptionStatus.PRODUCT_CATEGORY_NOT_FOUND));

        Product newProduct = requestDto.toEntity();
        newProduct.setProductCategory(productCategory);

        return new ProductResponseDto(productRepository.save(newProduct));
    }

    public void updateProduct(UUID productId, ProductRequestDto requestDto) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RepositoryException(ExceptionStatus.PRODUCT_NOT_FOUND));

        ProductCategory productCategory = productCategoryRepository.findById(requestDto.getProductCategoryId())
                .orElseThrow(() -> new RepositoryException(ExceptionStatus.PRODUCT_CATEGORY_NOT_FOUND));
        product.setProductCategory(productCategory);
        product.update(requestDto);
    }

    public void deleteProduct(UUID productId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RepositoryException(ExceptionStatus.PRODUCT_NOT_FOUND));

        productRepository.delete(product);
    }
}
