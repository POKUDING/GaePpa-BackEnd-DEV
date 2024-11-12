package com.sparta.gaeppa.cart.dto;

import java.util.List;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartListResponseDto {

    private UUID storeId;
    private int totalPrice;
    private List<CartProductResponseDto> items;

    @Builder
    public CartListResponseDto(UUID storeId, int totalPrice, List<CartProductResponseDto> items) {
        this.storeId = storeId;
        this.totalPrice = totalPrice;
        this.items = items;
    }
}
