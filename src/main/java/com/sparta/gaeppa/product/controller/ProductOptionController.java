package com.sparta.gaeppa.product.controller;

import static com.sparta.gaeppa.global.util.ApiResponseUtil.success;

import com.sparta.gaeppa.global.util.ApiResponseUtil.ApiResult;
import com.sparta.gaeppa.product.dto.ProductOptionListResponseDto;
import com.sparta.gaeppa.product.service.ProductOptionService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/products")
@RequiredArgsConstructor
public class ProductOptionController {

    private final ProductOptionService productOptionService;

    @GetMapping("/{productId}/options")
    public ResponseEntity<ApiResult<ProductOptionListResponseDto>> getProductOptions(@PathVariable UUID productId) {
        ProductOptionListResponseDto responseDto = productOptionService.getProductOptionsByProductId(productId);
        return new ResponseEntity<>(success(responseDto), HttpStatus.OK);
    }

    @PostMapping("/options")
    public String createProductOption() {
        return "Product Option Created";
    }

    @DeleteMapping("/options/{optionId}")
    public String deleteProductOption(@PathVariable String optionId) {
        return "Product Option Deleted";
    }
}
