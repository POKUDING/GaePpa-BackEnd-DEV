package com.sparta.gaeppa.cart.dto;

import java.util.List;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartProductResponseDto {

    private UUID productId;
    private String productName;
    private int productPrice;
    private int productQuantity;
    private List<CartProductOptionsDto> options;


    @Builder
    public CartProductResponseDto(UUID productId, String productName, int productPrice, int productQuantity,
                                  List<CartProductOptionsDto> options) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.options = options;
    }
}
