package com.sparta.gaeppa.order.dto;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class OrderProductDto {

    private String productName;
    private int productQuantity;
    private int productPrice;

    private List<OrderProductOptionListDto> productOptionList;

}
