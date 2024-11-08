package com.sparta.gaeppa.product.controller;

import com.sparta.gaeppa.product.service.ProductCategoryService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/products/option-categories")
@RequiredArgsConstructor
public class ProductOptionCategoryController {

    private final ProductCategoryService productCategoryService;
    
    @PostMapping("/")
    public String createProductOptionCategory() {
        return "Product Option Category Created";
    }

    @PutMapping("/")
    public String updateProductOptionCategory(@RequestParam("productid") UUID productId) {
        return "Product Option Category Updated";
    }

    @DeleteMapping("/{optionCategoryId}")
    public String deleteProductOptionCategory(@PathVariable String optionCategoryId) {
        return "Product Option Category Deleted";
    }
}
