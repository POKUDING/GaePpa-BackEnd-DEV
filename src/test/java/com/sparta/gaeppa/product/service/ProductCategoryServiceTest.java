package com.sparta.gaeppa.product.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.sparta.gaeppa.global.exception.ServiceException;
import com.sparta.gaeppa.product.dto.productCategory.ProductCategoryRequestDto;
import com.sparta.gaeppa.product.dto.productCategory.ProductCategoryResponseDto;
import com.sparta.gaeppa.product.entity.ProductCategory;
import com.sparta.gaeppa.product.repository.ProductCategoryRepository;
import com.sparta.gaeppa.product.repository.ProductRepository;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductCategoryServiceTest {

    @InjectMocks
    private ProductCategoryService productCategoryService;

    @Mock
    private ProductCategoryRepository productCategoryRepository;

    @Mock
    ProductRepository productRepository;

    @Test
    @DisplayName("상품 카테고리 생성 성공 테스트")
    void createProductCategory() {
        //given
        ProductCategoryRequestDto requestDto = new ProductCategoryRequestDto();
        requestDto.setCategoryName("면류");

        ProductCategory categoryEntity = requestDto.toEntity();

        given(productCategoryRepository.save(any(ProductCategory.class))).willReturn(categoryEntity);

        //when
        ProductCategoryResponseDto responseDto = productCategoryService.createProductCategory(requestDto);

        //then
        assertEquals(requestDto.getCategoryName(), responseDto.getName());
    }

    @Test
    @DisplayName("상품 카테고리 수정 성공 테스트")
    void updateProductCategory() {
        //given
        ProductCategoryRequestDto requestDto = new ProductCategoryRequestDto();
        requestDto.setCategoryName("면류");

        given(productCategoryRepository.findById(any(UUID.class))).willReturn(
                java.util.Optional.of(requestDto.toEntity()));

        //when-then
        productCategoryService.updateProductCategory(UUID.randomUUID(), requestDto);
    }

    @Test
    @DisplayName("카테고리 수정 실패 테스트 - 존재하지 않는 상품 카테고리")
    void updateProductCategoryFail() {
        //given
        ProductCategoryRequestDto requestDto = new ProductCategoryRequestDto();
        requestDto.setCategoryName("면류");

        given(productCategoryRepository.findById(any(UUID.class))).willReturn(
                java.util.Optional.empty());

        //when-then
        assertThrows(ServiceException.class,
                () -> productCategoryService.updateProductCategory(UUID.randomUUID(), requestDto));
    }

    @Test
    @DisplayName("상품 카테고리 삭제 성공 테스트")
    void deleteProductCategory() {
        //given
        UUID categoryId = UUID.randomUUID();

        ProductCategory productCategory = ProductCategory.builder().name("name").build();

        given(productCategoryRepository.findById(any(UUID.class))).willReturn(
                java.util.Optional.of(productCategory));

        //when-then
        productCategoryService.deleteProductCategory(categoryId);
    }

    @Test
    @DisplayName("카테고리 삭제 실패 테스트 - 존재하지 않는 상품 카테고리")
    void deleteProductCategoryFail() {
        //given
        UUID categoryId = UUID.randomUUID();

        given(productCategoryRepository.findById(any(UUID.class))).willReturn(
                java.util.Optional.empty());

        //when-then
        assertThrows(ServiceException.class,
                () -> productCategoryService.deleteProductCategory(categoryId));
    }

    @Test
    @DisplayName("상품 카테고리 삭제 실패 테스트 - 상품이 존재하는 상품 카테고리")
    void deleteProductCategoryFail2() {
        //given
        UUID categoryId = UUID.randomUUID();

        ProductCategory productCategory = ProductCategory.builder().name("name").build();

        given(productCategoryRepository.findById(any(UUID.class))).willReturn(
                java.util.Optional.of(productCategory));

        given(productRepository.existsByProductCategory(any(ProductCategory.class))).willReturn(true);

        //when-then
        assertThrows(ServiceException.class,
                () -> productCategoryService.deleteProductCategory(categoryId));
    }
}

