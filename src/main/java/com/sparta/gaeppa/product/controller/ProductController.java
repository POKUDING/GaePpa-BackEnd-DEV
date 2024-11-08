package com.sparta.gaeppa.product.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/products")
public class ProductController {

    @GetMapping("/")
    public String getProducts(@RequestParam("storeid") String storeId) {
        return "Products";
    }

    @GetMapping("/{productId}/options")
    public String getProductOptions(@PathVariable String productId) {
        return "Product Options";
    }

    @GetMapping("/pictures/{pictureId}")
    public String getProductPicture(@PathVariable String pictureId) {
        return "Product Picture";
    }

    @PostMapping("/")
    public String createProduct() {
        return "Product Created";
    }

    @PostMapping("/options")
    public String createProductOption() {
        return "Product Option Created";
    }

    @PostMapping("/option-categories")
    public String createProductOptionCategory() {
        return "Product Option Category Created";
    }

    @PostMapping("/categories")
    public String createProductCategory() {
        return "Product Category Created";
    }

    @PostMapping("/pictures")
    public String createProductPicture() {
        return "Product Picture Created";
    }

    @PutMapping("/{productId}")
    public String updateProduct(@PathVariable String productId) {
        return "Product Updated";
    }

    @PutMapping("/{productId}/option-categories")
    public String updateProductOptionCategory(@PathVariable String productId) {
        return "Product Option Category Updated";
    }

    @PutMapping("/{productId}/options")
    public String updateProductOption(@PathVariable String productId) {
        return "Product Option Updated";
    }

    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable String productId) {
        return "Product Deleted";
    }

    @DeleteMapping("/option-categories/{optionCategoryId}")
    public String deleteProductOptionCategory(@PathVariable String optionCategoryId) {
        return "Product Option Category Deleted";
    }

    @DeleteMapping("/options/{optionId}")
    public String deleteProductOption(@PathVariable String optionId) {
        return "Product Option Deleted";
    }

    @DeleteMapping("/pictures/{pictureId}")
    public String deleteProductPicture(@PathVariable String pictureId) {
        return "Product Picture Deleted";
    }
}
