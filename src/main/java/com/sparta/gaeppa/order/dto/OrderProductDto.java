package com.sparta.gaeppa.order.dto;

import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderProductDto {

    private String productName;
    private int productQuantity;
    private int productPrice;

    private List<OrderProductOptionListDto> productOptionList;

    @Builder
    private OrderProductDto(String productName, int productQuantity, int productPrice,
                            List<OrderProductOptionListDto> productOptionList) {
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.productPrice = productPrice;
        this.productOptionList = productOptionList;
    }
}
