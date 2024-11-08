package com.sparta.gaeppa.product.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/products/pictures")
@RequiredArgsConstructor
public class ProductPicturesController {

    @GetMapping("/{pictureId}")
    public String getProductPicture(@PathVariable String pictureId) {
        return "Product Picture";
    }

    @PostMapping("/")
    public String createProductPicture() {
        return "Product Picture Created";
    }

    @DeleteMapping("/{pictureId}")
    public String deleteProductPicture(@PathVariable String pictureId) {
        return "Product Picture Deleted";
    }
}
