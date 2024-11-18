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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductRepository productRepository;

    @Transactional
    public StoreProductListResponseDto getAllProductsByStoreId(UUID storeId) {

        //TODO: store 레포지토리가 완성되면 조회 및 예외처리 로직 추가
//        storeId = null;
        List<ProductCategory> productCategoryList = productCategoryRepository.findAllByStore_StoreId(storeId);

        if (productCategoryList.isEmpty()) {
            throw new ServiceException(ExceptionStatus.PRODUCT_CATEGORY_NOT_FOUND);
        }

        int totalAmount = productRepository.countByProductCategory_Store_StoreId(storeId);

        return new StoreProductListResponseDto(totalAmount, productCategoryList);
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

    @Transactional
    public Page<ProductResponseDto> searchProducts(String productName, String categoryName, String optionName,
                                                   String optionCategoryName, String storeCategoryName,
                                                   String sortBy,
                                                   String sortDirection,
                                                   int page,
                                                   int size) {
        Pageable pageable = PageRequest.of(page - 1, size);

        // Querydsl 동적 검색 수행
        Page<Product> products = productRepository.searchProducts(productName, categoryName, optionName,
                optionCategoryName, storeCategoryName, pageable, sortBy, sortDirection);

        // DTO 변환
        List<ProductResponseDto> responseDtoList = products.stream()
                .map(ProductResponseDto::from)
                .toList();

        return new PageImpl<>(responseDtoList, pageable, products.getTotalElements());
    }
}
