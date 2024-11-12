package com.sparta.gaeppa.order.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderProductDto {

    private String productName;
    private int productQuantity;
    private int productPrice;

    private List<OrderProductOptionListDto> productOptionList;
}
