package com.sparta.gaeppa.product.controller;

import static com.sparta.gaeppa.global.util.ApiResponseUtil.success;

import com.sparta.gaeppa.global.util.ApiResponseUtil.ApiResult;
import com.sparta.gaeppa.product.dto.product.ProductRequestDto;
import com.sparta.gaeppa.product.dto.product.ProductResponseDto;
import com.sparta.gaeppa.product.dto.product.StoreProductListResponseDto;
import com.sparta.gaeppa.product.service.ProductService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/product-categories/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<ApiResult<StoreProductListResponseDto>> getProducts(@RequestParam("storeid") UUID storeId) {

        StoreProductListResponseDto responseDto = productService.getAllProductsByStoreId(storeId);

        return new ResponseEntity<>(success(responseDto), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResult<ProductResponseDto>> createProduct(@RequestBody ProductRequestDto requestDto) {

        ProductResponseDto productResponseDto = productService.createProduct(requestDto);

        return new ResponseEntity<>(success(productResponseDto), HttpStatus.CREATED);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ApiResult<String>> updateProduct(@PathVariable UUID productId,
                                                           @RequestBody ProductRequestDto requestDto) {

        productService.updateProduct(productId, requestDto);

        return new ResponseEntity<>(success("Update Product Success"), HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<ApiResult<String>> deleteProduct(@PathVariable UUID productId) {

        productService.deleteProduct(productId);

        return new ResponseEntity<>(success("Delete Product Success"), HttpStatus.NO_CONTENT);
    }
}
