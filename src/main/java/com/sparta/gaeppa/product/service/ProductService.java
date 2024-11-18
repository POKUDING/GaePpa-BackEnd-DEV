package com.sparta.gaeppa.product.service;

import com.sparta.gaeppa.global.exception.ExceptionStatus;
import com.sparta.gaeppa.global.exception.ServiceException;
import com.sparta.gaeppa.members.entity.MemberRole;
import com.sparta.gaeppa.product.dto.product.ProductRequestDto;
import com.sparta.gaeppa.product.dto.product.ProductResponseDto;
import com.sparta.gaeppa.product.dto.product.StoreProductListResponseDto;
import com.sparta.gaeppa.product.entity.Product;
import com.sparta.gaeppa.product.entity.ProductCategory;
import com.sparta.gaeppa.product.repository.ProductCategoryRepository;
import com.sparta.gaeppa.product.repository.ProductRepository;
import com.sparta.gaeppa.security.jwts.entity.CustomUserDetails;
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
    public void deleteProduct(UUID productId, CustomUserDetails userDetails) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ServiceException(ExceptionStatus.PRODUCT_NOT_FOUND));

        if (userDetails.getMemberRole() != MemberRole.MASTER && userDetails.getMemberRole() != MemberRole.MANAGER
                && !product.getCreatedBy().equals(userDetails.getUsername())) {
            throw new ServiceException(ExceptionStatus.UNAUTHORIZED);
        }

        productRepository.delete(product);
    }
}
