package com.sparta.gaeppa.product.controller;

import static com.sparta.gaeppa.global.util.ApiResponseUtil.success;

import com.sparta.gaeppa.global.util.ApiResponseUtil.ApiResult;
import com.sparta.gaeppa.product.dto.ProductOptionCategoryRequestDto;
import com.sparta.gaeppa.product.dto.ProductOptionCategoryResponseDto;
import com.sparta.gaeppa.product.service.ProductOptionCategoryService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/products/option-categories")
@RequiredArgsConstructor
public class ProductOptionCategoryController {

    private final ProductOptionCategoryService productOptionCategoryService;

    @PostMapping
    public ResponseEntity<ApiResult<ProductOptionCategoryResponseDto>> createProductOptionCategory(
            @RequestBody ProductOptionCategoryRequestDto requestDto) {

        ProductOptionCategoryResponseDto productOptionCategoryResponseDto = productOptionCategoryService.createProductOptionCategory(
                requestDto);

        return new ResponseEntity<>(success(productOptionCategoryResponseDto), HttpStatus.CREATED);
    }

    @PutMapping("/{optionCategoryId}")
    public ResponseEntity<ApiResult<String>> updateProductOptionCategory(@PathVariable UUID optionCategoryId,
                                                                         @RequestBody ProductOptionCategoryRequestDto requestDto) {

        productOptionCategoryService.updateProductOptionCategory(optionCategoryId, requestDto);

        return new ResponseEntity<>(success("Update Product Option Category Success"), HttpStatus.OK);
    }

    @DeleteMapping("/{optionCategoryId}")
    public ResponseEntity<ApiResult<String>> deleteProductOptionCategory(@PathVariable UUID optionCategoryId) {

        productOptionCategoryService.deleteProductOptionCategory(optionCategoryId);

        return new ResponseEntity<>(success("Delete Product Option Category Success"), HttpStatus.OK);
    }
}
